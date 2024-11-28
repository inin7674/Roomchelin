package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StoreSvc {

    @Autowired
    private NaverApi naverApi;

    @Autowired
    private StoreRepository storeRepository;

    public Store save(String storename) {
        Map<String, Object> storeapi = naverApi.naverapi(storename);
        System.out.println(storeapi);

        // API 응답에서 필요한 데이터 추출
        String name = ((String) storeapi.get("title")).replaceAll("<[^>]*>","");
        String category = (String) storeapi.get("category");
        String address = (String) storeapi.get("roadAddress");
        String telephone = (String) storeapi.get("telephone");
        Integer mapx = (Integer) storeapi.get("mapx");
        Integer mapy = (Integer) storeapi.get("mapy");


        // StoreDTO 객체 생성 및 반환
        Store store = new Store();
        store.setStore_name(name);
        store.setCategory(category);
        store.setRoad_address(address);
        store.setPhone_num(telephone);
        store.setMapx(mapx);
        store.setMapy(mapy);


        return storeRepository.save(store);
    }


//    public StoreDTO savestore() {
//
//    }
}
