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

    public StoreDTO test(String storename) {
        Map<String, Object> storeapi = naverApi.naverapi(storename);
        System.out.println(storeapi);

        // API 응답에서 필요한 데이터 추출
        String name = (String) storeapi.get("title");
        String category = (String) storeapi.get("category");
        String address = (String) storeapi.get("roadAddress");
        String telephone = (String) storeapi.get("telephone");

        // StoreDTO 객체 생성 및 반환
        return new StoreDTO(name, category, address, telephone);
    }


//    public StoreDTO savestore() {
//
//    }
}
