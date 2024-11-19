package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;


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

    @GetMapping("/search/{param}")
    public EscaperoomDTO search(@PathVariable String param) {
        return escaperoomSvc.search(param);
    }

    @GetMapping("/findall")
    public List<EscaperoomDTO> findall() {
        return escaperoomSvc.findall();
    }
//    @GetMapping("/test")
//    public void test(){
//        escaperoomSvc.test();
//    }
//    @PostMapping("/savestore")
//    public void savestore() {
//        storeSvc.savestore();
//    }

}
