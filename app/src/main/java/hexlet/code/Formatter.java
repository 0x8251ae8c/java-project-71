package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String generate(Map<String, Object> map1,
                                  Map<String, Object> map2,
                                  Map<String, String> diff,
                                  String format) throws JsonProcessingException {
        return switch (format) {
            case "stylish" -> Stylish.generate(map1, map2, diff);
            case "plain" -> Plain.generate(map1, map2, diff);
            case "json" -> Json.generate(map1, map2, diff);
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }
}
