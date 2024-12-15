package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, Object>> compareResult, String format) {
        return Stylish.format(compareResult);
    }
}
