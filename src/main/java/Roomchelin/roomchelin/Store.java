package Roomchelin.roomchelin;

import jakarta.persistence.*;

@Entity
public class Store {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "store_name")
    private String storeName;
    private String category;
    private String road_address;
    private String phone_num;
    private Integer mapx;
    private Integer mapy;
    private String place_link;

    public Store() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getstoreName() {
        return storeName;
    }

    public void setstoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRoad_address() {
        return road_address;
    }

    public void setRoad_address(String road_address) {
        this.road_address = road_address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
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

//    public String getPlace_link() {
//        return place_link;
//    }
//
//    public void setPlace_link(String place_link) {
//        this.place_link = place_link;
//    }
}
