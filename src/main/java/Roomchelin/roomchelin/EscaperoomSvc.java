package Roomchelin.roomchelin;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.management.remote.JMXConnectorFactory.connect;

@Service
public class EscaperoomSvc {

    @Autowired
    private EscaperoomRepository escaperoomrepository;

    public void update(){
        List<Escaperoom> list = escaperoomrepository.findAll();

        for (Escaperoom room : list) {
            String storeName = room.getStore_name();

            EscaperoomDTO dto = save(storeName);

            if (dto != null){
                room.setRoad_adderss(dto.road_address());
                room.setTelephone(dto.telephone());
                room.setMapx(dto.mapx());
                room.setMapy(dto.mapy());
            }

            escaperoomrepository.save(room);
        }



    }

    public EscaperoomDTO save(String param) {

        String clientId = "tWS0Ne1OXpW29Qe8YlMg";
        String clientSecret = "sAAzSFrr2w";

        String text = param;
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }


        String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + text + "&display=5";    // JSON 결과

        //헤더 추가
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        // 검색 결과가 없으면 null 반환
        JSONObject jsonResponse = new JSONObject(responseBody);
        try {
            // "items" 키가 존재하는지 확인
            if (!jsonResponse.has("items") || jsonResponse.getJSONArray("items").length() == 0) {
                System.out.println("잘못된 매장이름: " + param);
                return null;
            }

            // "items" 배열에서 필요한 값들을 파싱
            String category = jsonResponse.getJSONArray("items").getJSONObject(0).getString("category");
            String roadaddr = jsonResponse.getJSONArray("items").getJSONObject(0).getString("roadAddress");
            String telephone = jsonResponse.getJSONArray("items").getJSONObject(0).optString("telephone",null);
            Integer mapx = jsonResponse.getJSONArray("items").getJSONObject(0).getInt("mapx");
            Integer mapy = jsonResponse.getJSONArray("items").getJSONObject(0).getInt("mapy");

            // 데이터를 DTO에 담기
            EscaperoomDTO dto = new EscaperoomDTO(
                    null,
                    null,
                    null,
                    category,
                    roadaddr,
                    telephone,
                    mapx,
                    mapy
            );

            return dto;

        } catch (JSONException e) {
            // JSON 파싱 중 오류 발생 시 처리
            System.out.println("JSON 파싱 오류: " + e.getMessage());
            return null;
        }
    }
        private static String get(String apiUrl, Map<String, String> requestHeaders){
            HttpURLConnection con = connect(apiUrl);
            try {
                con.setRequestMethod("GET");
                for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                    con.setRequestProperty(header.getKey(), header.getValue());
                }


                int responseCode = con.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                    return readBody(con.getInputStream());
                } else { // 오류 발생
                    return readBody(con.getErrorStream());
                }
            } catch (IOException e) {
                throw new RuntimeException("API 요청과 응답 실패", e);
            } finally {
                con.disconnect();
            }
        }


        private static HttpURLConnection connect(String apiUrl){
            try {
                URL url = new URL(apiUrl);
                return (HttpURLConnection)url.openConnection();
            } catch (MalformedURLException e) {
                throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
            } catch (IOException e) {
                throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
            }
        }


        private static String readBody(InputStream body){
            InputStreamReader streamReader = new InputStreamReader(body);


            try (BufferedReader lineReader = new BufferedReader(streamReader)) {
                StringBuilder responseBody = new StringBuilder();


                String line;
                while ((line = lineReader.readLine()) != null) {
                    responseBody.append(line);
                }


                return responseBody.toString();
            } catch (IOException e) {
                throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
            }
        }
    }

