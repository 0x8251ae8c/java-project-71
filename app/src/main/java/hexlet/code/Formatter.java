package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String generate(Map<String, Object> map1,
                                  Map<String, Object> map2,
                                  Map<String, String> diff,
                                  String format) {
        return switch (format) {
            case "stylish" -> Stylish.generate(map1, map2, diff);
            default -> throw new IllegalStateException("Unexpected value: " + format);
        };
    }
}
