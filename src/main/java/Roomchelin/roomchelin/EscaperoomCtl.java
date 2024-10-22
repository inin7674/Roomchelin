package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EscaperoomCtl {

    @Autowired
    private EscaperoomSvc escaperoomSvc;

    @GetMapping("/test")
    public void test(){
        escaperoomSvc.update();
    }


}
