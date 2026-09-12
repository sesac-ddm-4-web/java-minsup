package note2;

import java.util.HashMap;
import java.util.Map;

public class MapPrices {
    public static void main(String[] args) {
        Map<String, Integer> mapprices = new HashMap<>();
        mapprices.put("커피", 3000);
        mapprices.put("라떼", 4000);
        mapprices.put("차", 2500);
        mapprices.put("커피", 3500);
        mapprices.remove("차");
        System.out.println("커피: " + mapprices.get("커피"));
        System.out.println("메뉴 수: " + mapprices.size());
        System.out.println("차 포함: " + mapprices.containsKey("차"));

    }
}
