package hexlet.code.formatters;

import java.util.Map;
import java.util.StringJoiner;

public class Plain {
    public static String format(Map<String, Map<String, Object>> compareResult) {
        var sj = new StringJoiner("\n");

        for (var item : compareResult.entrySet()) {
            var fieldName = item.getKey();
            var fieldValue = item.getValue();
            var fieldStatus = fieldValue.get("status").toString();

            if (fieldStatus.equals("without change")) {
                continue;
            }

            var line = "Property '" + fieldName + switch (fieldStatus) {
                case "removed" -> "' was removed";
                case "added" -> "' was added with value: " + getValueAsString(fieldValue, "newValue");
                case "changed" -> "' was updated. From " + getValueAsString(fieldValue, "oldValue") + " to "
                        + getValueAsString(fieldValue, "newValue");
                default -> throw new RuntimeException("Unknown status of fieldName");
            };
            sj.add(line);
        }
        return sj.toString();
    }

    private static String getValueAsString(Map<String, Object> map, String key) {
        var value = map.get(key);

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
