package Roomchelin.roomchelin;

import jakarta.persistence.*;

@Entity
public class Escaperoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "escape_store")
    private String escapestore; // 매장이름

    @Column(name = "region_main")
    private String regionmain; // 지역 예시 : 서울 , 부산

    @Column(name = "region_sub")
    private String regionsub; // 행정구역 예시 : 강남구, 서초구

    @Column(name = "road_address")
    private String roadaddress; // 매장 도로명 주소
    private String telephone; // 매장 전화번호
    private Integer mapx; // 매장 위도
    private Integer mapy; // 매장 경도


    public Escaperoom() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEscapestore() {
        return escapestore;
    }

    public void setEscapestore(String escapestore) {
        this.escapestore = escapestore;
    }

    public String getRegionmain() {
        return regionmain;
    }

    public void setRegionmain(String regionmain) {
        this.regionmain = regionmain;
    }

    public String getRegionsub() {
        return regionsub;
    }

    public void setRegionsub(String regionsub) {
        this.regionsub = regionsub;
    }

    public String getRoadaddress() {
        return roadaddress;
    }

    public void setRoadaddress(String roadaddress) {
        this.roadaddress = roadaddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getMapx() {
        return mapx;
    }

    public void setMapx(Integer mapx) {
        this.mapx = mapx;
    }

    public Integer getMapy() {
        return mapy;
    }

    public void setMapy(Integer mapy) {
        this.mapy = mapy;
    }
}

