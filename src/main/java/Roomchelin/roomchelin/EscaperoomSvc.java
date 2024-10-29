package Roomchelin.roomchelin;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    private NaverApi naverApi;

    public void escapeupdate() {
        List<Escaperoom> list = escaperoomrepository.findAll();

        for (Escaperoom room : list) {
            String storeName = room.getStore_name();

            Map<String, Object> result = naverApi.naverapi(storeName);

            if (result != null) {
                room.setRoad_adderss(result.get("roadaddr").toString());
                room.setTelephone(result.get("telephone").toString());
                room.setMapx((Integer) result.get("mapx"));
                room.setMapy((Integer) result.get("mapy"));
            }


//            EscaperoomDTO dto = new EscaperoomDTO(
//                    null,
//                    null,
//                    null,
//                    room.getRoad_adderss(),
//                    room.getTelephone(),
//                    room.getMapx(),
//                    room.getMapy()
//            );

            escaperoomrepository.save(room);
        }


    }

    public EscaperoomDTO save(String param) {

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

    }
}

