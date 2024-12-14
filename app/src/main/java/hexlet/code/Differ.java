package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        var map1 = Parser.parseToMap(filepath1);
        var map2 = Parser.parseToMap(filepath2);

        return compare(map1, map2);
    }

    private static String compare(Map<String, String> map1, Map<String, String> map2) {
        var map = new HashMap<>(map1);
        map.putAll(map2);

        var sj = new StringJoiner("\n");

        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    var key = entry.getKey();
                    var value = entry.getValue();
                    var firstMapContainsKey = map1.containsKey(key);
                    var secondMapContainsKey = map2.containsKey(key);

                    if (firstMapContainsKey && !secondMapContainsKey) {
                        sj.add("  - " + key + ": " + value);
                    } else if (!firstMapContainsKey && secondMapContainsKey) {
                        sj.add("  + " + key + ": " + value);
                    } else {
                        var value1 = map1.get(key);
                        if (value1.equals(value)) {
                            sj.add("    " + key + ": " + value);
                        } else {
                            sj.add("  - " + key + ": " + value1);
                            sj.add("  + " + key + ": " + value);
                        }
                    }
                });

        return "{\n" + sj + "\n}";
    }
}
