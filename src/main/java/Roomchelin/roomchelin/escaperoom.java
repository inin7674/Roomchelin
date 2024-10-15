package Roomchelin.roomchelin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class escaperoom {
    @GeneratedValue
    @Id
    private Long id;

    private String region_main;
    private String region_sub;
    private String store_name;
    private String road_adderss;
    private String phone_num;
    private double mapx;
    private double mapy;


    public escaperoom() {
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
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getRoad_adderss() {
        return road_adderss;
    }

    public void setRoad_adderss(String road_adderss) {
        this.road_adderss = road_adderss;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
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
