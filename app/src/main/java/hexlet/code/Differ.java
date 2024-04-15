package hexlet.code;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Differ {
    public static String generate(File filepath1, File filepath2) throws Exception {
        String result = "";

        Map<String, Object> map1 = Parser.parse(filepath1);
        Map<String, Object> map2 = Parser.parse(filepath2);
        Map<String, Object> sortedMap = new TreeMap<>(map1);
        sortedMap.putAll(map2);

        Set<String> keys1 = map1.keySet();
        Set<String> keys2 = map2.keySet();

        for (String key : sortedMap.keySet()) {
            String value1 = String.valueOf(map1.get(key));
            String value2 = String.valueOf(map2.get(key));

            if (!keys2.contains(key)) {
                result += "  - " + key + ": " + value1 + "\n";
            } else if (!keys1.contains(key)) {
                result += "  + " + key + ": " + value2 + "\n";
            } else {
                if (value1.equals(value2)) {
                    result += "    " + key + ": " + value1 + "\n";
                } else {
                    result += "  - " + key + ": " + value1 + "\n";
                    result += "  + " + key + ": " + value2 + "\n";
                }
            }
        }
        return "{\n" + result + "}";
    }
}
