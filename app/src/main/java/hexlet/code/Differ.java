package hexlet.code;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(File filepath1, File filepath2) throws Exception {
        String result = "";

        Map<String, String> map1 = Parser.parse(filepath1);
        Map<String, String> map2 = Parser.parse(filepath2);
        Map<String, String> sortedMap = new TreeMap<>(map1);
        sortedMap.putAll(map2);

        result += "{\n";

        for (var entry : sortedMap.entrySet()) {
            var key = String.valueOf(entry.getKey());
            var value = String.valueOf(entry.getValue());

            var value1 = map1.get(key);
            var value2 = map2.get(key);

            if (value.equals(value1) & value.equals(value2)) {
                result += "    " + key + ": " + value + "\n";
            } else if (value.equals(value1)) {
                result += "  - " + key + ": " + value + "\n";
            } else if (value.equals(value2)) {
                if (value1 != null) {
                    result += "  - " + key + ": " + value1 + "\n";
                }
                result += "  + " + key + ": " + value + "\n";
            }
        }

        result += "}";

        return result;
    }
}
