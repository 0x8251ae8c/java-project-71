package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<ComparatorKeys, Object>> compareResult, String format) throws Exception {
        return switch (format) {
            case "stylish" -> Stylish.format(compareResult);
            case "plain" -> Plain.format(compareResult);
            case "json" -> Json.format(compareResult);
            default -> throw new RuntimeException("Unknown differ format");
        };
    }
}
