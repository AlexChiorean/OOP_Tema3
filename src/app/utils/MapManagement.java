package app.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Comparator;


public class MapManagement {
    /**
     * Sorts the hash map by the value, and returns the first 5 entries.
     *
     * @param hashMap
     * @return sorted top 5
     */
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

    /**
     * Adds the contents of the map to a node.
     */
    public static void addMapToNode(final ArrayNode outputs,
                                    final String field,
                                    final Map<String, Integer> map) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        ObjectNode nodeList = objectMapper.createObjectNode();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            nodeList.put(entry.getKey(), entry.getValue());
        }
        objectNode.put(field, nodeList);
        outputs.add(objectNode);
    }
}
