package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StoreSvc {

    @Autowired
    private NaverApi naverApi;

    @Autowired
    private StoreRepository storeRepository;

    public Store save(String storename) {
        // 중복이 아닌 경우에만 네이버 API 호출
        Map<String, Object> storeapi = naverApi.naverapi(storename);
        System.out.println(storeapi);

        // API 응답에서 필요한 데이터 추출
        String name = ((String) storeapi.get("title")).replaceAll("<[^>]*>", "");

        // 먼저 데이터베이스에서 중복 체크
        Store existingStore = storeRepository.findByStoreName(name);

        if (existingStore != null) {
            System.out.println("중복되는 가게명: " + name);
            return null;
        }

        String category = (String) storeapi.get("category");
        String address = (String) storeapi.get("roadAddress");
        String telephone = (String) storeapi.get("telephone");
        Integer mapx = (Integer) storeapi.get("mapx");
        Integer mapy = (Integer) storeapi.get("mapy");

        // Store 객체 생성 및 설정
        Store store = new Store();
        store.setstoreName(name);
        store.setCategory(category);
        store.setRoad_address(address);
        store.setPhone_num(telephone);
        store.setMapx(mapx);
        store.setMapy(mapy);


        return storeRepository.save(store);
    }
    public List<Store> findall() {
        return storeRepository.findAll();
    }


//    public StoreDTO savestore() {
//
//    }
}
