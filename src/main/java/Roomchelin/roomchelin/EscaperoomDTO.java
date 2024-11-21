package Roomchelin.roomchelin;

public record EscaperoomDTO(
        String escape_store,
        String region_main,
        String region_sub,
        String road_address,
        String telephone,
        Integer mapx,
        Integer mapy
) {
    public EscaperoomDTO(String escape_store, String region_main, String region_sub) {
        this(escape_store, region_main, region_sub, null, null, null, null);
    }

}
