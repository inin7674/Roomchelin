package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreCtl {

    @Autowired
    private StoreSvc storeSvc;

    @GetMapping("/save/{storename}")
    public Store save(@PathVariable String storename) {
        return storeSvc.save(storename);
    }
}
