package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreCtl {

    @Autowired
    private StoreSvc storeSvc;

    @GetMapping("/test/{storename}")
    public StoreDTO test(@PathVariable String storename) {
        return storeSvc.test(storename);

    }
}
