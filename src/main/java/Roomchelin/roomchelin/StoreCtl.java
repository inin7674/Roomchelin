package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreCtl {

    @Autowired
    private StoreSvc storeSvc;

    @PostMapping("/storesave/{storename}")
    public Store save(@PathVariable String storename) {
        return storeSvc.save(storename);
    }

    @GetMapping("/storefindall")
    public List<Store> findall(){
        return storeSvc.findall();
    }
}
