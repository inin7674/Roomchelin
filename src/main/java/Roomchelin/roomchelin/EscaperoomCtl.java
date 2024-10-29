package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;


@RestController
public class EscaperoomCtl {

    @Autowired
    private EscaperoomSvc escaperoomSvc;

    @Autowired
    private StoreSvc storeSvc;

    @GetMapping("/esupdate")
    public void esupdate(){
        escaperoomSvc.escapeupdate();
    }

    @GetMapping("/test")
    public void test(){
        escaperoomSvc.test();
    }
//    @PostMapping("/savestore")
//    public void savestore() {
//        storeSvc.savestore();
//    }

}
