package hexlet.code.formatters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    public static String generate(Map<String, Object> map1,
                                  Map<String, Object> map2,
                                  Map<String, String> diff) throws JsonProcessingException {
        Map<String, Object> map = new TreeMap<>();

        for (var key : diff.keySet()) {
            String valueState = diff.get(key);
            Map<String, Object> value = new LinkedHashMap<>();

            switch (valueState) {
                case "removed":
                    value.put("state", valueState);
                    value.put("value", map1.get(key));
                    map.put(key, value);
                    break;
                case "added", "kept":
                    value.put("state", valueState);
                    value.put("value", map2.get(key));
                    map.put(key, value);
                    break;
                case "changed":
                    value.put("state", valueState);
                    value.put("oldValue", map1.get(key));
                    value.put("newValue", map2.get(key));
                    map.put(key, value);
                    break;
                default:
                    map.put(key, "unknown value state");
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }
}
