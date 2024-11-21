package Roomchelin.roomchelin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static javax.management.remote.JMXConnectorFactory.connect;

@Service
public class EscaperoomSvc {

    @Autowired
    private EscaperoomRepository escaperoomrepository;

    @Autowired
    private NaverApi naverApi;

    public void escapeupdate() {
        List<Escaperoom> list = escaperoomrepository.findAll();

        for (Escaperoom room : list) {
            String storeName = room.getEscapestore();

            Map<String, Object> result = naverApi.naverapi(storeName);

            if (result != null) {
                room.setRoadaddress(result.get("roadaddr").toString());
                room.setTelephone(result.get("telephone").toString());
                room.setMapx((Integer) result.get("mapx"));
                room.setMapy((Integer) result.get("mapy"));
            }
            escaperoomrepository.save(room);
        }


    }

    public EscaperoomDTO search(String param) {
        Escaperoom escaperoom = escaperoomrepository.findByEscapestoreContaining(param);
        return new EscaperoomDTO(
                escaperoom.getEscapestore(),
                escaperoom.getRegionmain(),
                escaperoom.getRegionsub()
        );
    }

    public List<EscaperoomDTO> findall() {
        return escaperoomrepository.findAll()
                .stream()
                .map(e -> new EscaperoomDTO(
                        e.getEscapestore(),
                        e.getRegionmain(),
                        e.getRegionsub(),
                        e.getRoadaddress(),
                        e.getTelephone(),
                        e.getMapy(),
                        e.getMapy()
                )).toList();
    }

//    public EscaperoomDTO findbyid(Long id) {
//        Escaperoom escaperoom = escaperoomrepository.findById(id).orElse(null);
//        return new EscaperoomDTO(
//                escaperoom.getEscape_store(),
//                escaperoom.getRegion_main(),
//                escaperoom.getRegion_sub(),
//                escaperoom.getRoad_address(),
//                escaperoom.getTelephone(),
//                escaperoom.getMapx(),
//                escaperoom.getMapy()
//        );
//    }
}

