package Roomchelin.roomchelin;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CSVToDatabase {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://13.124.237.2:3306/Roomchelin";
        String username = "user";
        String password = "user";

        String csvFilePath = "C:\\Users\\A\\Downloads\\escaperoom.csv";  // 파일 경로 수정

        // SQL 쿼리 템플릿: 필요한 컬럼에만 값을 넣음
        String sql = "INSERT INTO escaperoom (region_main, region_sub, escape_store) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {

            PreparedStatement statement = connection.prepareStatement(sql);

            // InputStreamReader로 인코딩 설정 (EUC-KR)
            BufferedReader lineReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(csvFilePath), "EUC-KR"));

            String lineText = null;
            lineReader.readLine(); // 첫 줄을 무시 (헤더)

            // 파일을 한 줄씩 읽음
            while ((lineText = lineReader.readLine()) != null) {
                // 데이터를 탭으로 분리
                String[] data = lineText.split(",");  // 탭 구분자 사용

                // 분리된 데이터가 3개 이상인 경우에만 처리
                if (data.length >= 3) {
                    String regionMain = data[0];
                    String regionSub = data[1];
                    String storeName = data[2];

                    // SQL 쿼리에 값 설정
                    statement.setString(1, regionMain);
                    statement.setString(2, regionSub);
                    statement.setString(3, storeName);

                    // 데이터 삽입 실행
                    statement.addBatch();
                } else {
                    // 데이터가 불완전한 경우 출력 (디버깅용)
                    System.out.println("Skipping invalid line: " + lineText);
                }
            }

            // 모든 데이터 삽입
            statement.executeBatch();
            lineReader.close();

            System.out.println("Data has been inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}