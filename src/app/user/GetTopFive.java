package app.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Comparator;

public class GetTopFive {
    public static Map<String, Integer> getTopFive(final Map<String, Integer> hashMap) {

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(hashMap.entrySet());
        Comparator<Map.Entry<String, Integer>> byValue = Map.Entry.comparingByValue();
        entryList.sort(byValue);

        int count = 0;
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            count++;
            sortedMap.put(entry.getKey(), entry.getValue());
            if (count == 5) return sortedMap;
        }
        return sortedMap;
    }
}
