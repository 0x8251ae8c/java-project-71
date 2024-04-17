package hexlet.code;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Differ {
    public static String generate(File filepath1, File filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(File filepath1, File filepath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);

        Map<String, String> diff = generateDiff(map1, map2);

        return Formatter.generate(map1, map2, diff, format);
    }

    private static Map<String, String> generateDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> map = new HashMap<>(map1);
        map.putAll(map2);

        Set<String> keysMap1 = map1.keySet();
        Set<String> keysMap2 = map2.keySet();

        Map<String, String> diff = new TreeMap<>();

        for (String key : map.keySet()) {
            String valueState;
            if (!keysMap2.contains(key)) {
                valueState = "removed";
            } else if (!keysMap1.contains(key)) {
                valueState = "added";
            } else {
                String value1 = String.valueOf(map1.get(key));
                String value2 = String.valueOf(map2.get(key));
                if (value1.equals(value2)) {
                    valueState = "kept";
                } else {
                    valueState = "changed";
                }
            }
            diff.put(key, valueState);
        }
        return diff;
    }
}
