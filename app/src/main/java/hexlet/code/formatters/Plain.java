package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Plain {
    public static String format(List<Map<String, Object>> compareResult) {
        var sj = new StringJoiner("\n");

        for (var item : compareResult) {
            var field = item.get("field").toString();
            var status = item.get("status").toString();
            var value = valueToString(item.get("value"));

            switch (status) {
                case "removed":
                    sj.add("Property '" + field + "' was removed");
                    break;
                case "added":
                    sj.add("Property '" + field + "' was added with value: " + value);
                    break;
                case "without change":
                    break;
                case "changed":
                    var newValue = valueToString(item.get("newValue"));
                    sj.add("Property '" + field + "' was updated. From " + value + " to " + newValue);
                    break;
                default:
                    throw new RuntimeException("Unknown status of field");
            }
        }
        return sj.toString();
    }

    private static String valueToString(Object value) {
        if (value.equals("null")) {
            return "null";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        if (value instanceof Boolean || value instanceof Integer || value instanceof Long) {
            return value.toString();
        }

        if (value instanceof Float || value instanceof Double) {
            return value.toString();
        }

        return "[complex value]";
    }
}
