package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        var map1 = parseJsonToMap(filepath1);
        var map2 = parseJsonToMap(filepath2);

        return compare(map1, map2);
    }

    private static String compare(Map<String, String> map1, Map<String, String> map2) {
        var map = new HashMap<String, String>(map1);
        map.putAll(map2);

        var sj = new StringJoiner("\n");

        map.entrySet().stream()
                .sorted(Map.Entry.<String, String>comparingByKey())
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

    private static Map<String, String> parseJsonToMap(String filepath) throws Exception{
        var mapper = new ObjectMapper();
        var content = readFile(filepath);

        return mapper.readValue(content, new TypeReference<Map<String, String>>(){});
    }

    private static String readFile(String filepath) throws Exception{
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path);
    }
}
