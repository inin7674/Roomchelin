package Roomchelin.roomchelin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Escaperoom {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String escape_store; // 매장이름
    private String region_main; // 지역 예시 : 서울 , 부산
    private String region_sub; // 행정구역 예시 : 강남구, 서초구
    private String road_adderss; // 매장 도로명 주소
    private String telephone; // 매장 전화번호
    private double mapx; // 매장 위도
    private double mapy; // 매장 경도


    public Escaperoom() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion_main() {
        return region_main;
    }

    public void setRegion_main(String region_main) {
        this.region_main = region_main;
    }

    public String getRegion_sub() {
        return region_sub;
    }

    public void setRegion_sub(String region_sub) {
        this.region_sub = region_sub;
    }

    public String getStore_name() {
        return escape_store;
    }

    public void setStore_name(String escape_store) {
        this.escape_store = escape_store;
    }

    public String getRoad_adderss() {
        return road_adderss;
    }

    public void setRoad_adderss(String road_adderss) {
        this.road_adderss = road_adderss;
    }

    public String getPhone_num() {
        return telephone;
    }

    public void setPhone_num(String telephone) {
        this.telephone = telephone;
    }

    public double getMapx() {
        return mapx;
    }

    public void setMapx(double mapx) {
        this.mapx = mapx;
    }

    public double getMapy() {
        return mapy;
    }

    public void setMapy(double mapy) {
        this.mapy = mapy;
    }
}
