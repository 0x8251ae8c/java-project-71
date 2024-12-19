package hexlet.code.formatters;

import java.util.List;
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
                case "added" -> "' was added with value: " + getValueAsString(fieldValue.get("newValue"));
                case "changed" -> "' was updated. From " + getValueAsString(fieldValue.get("oldValue")) + " to "
                        + getValueAsString(fieldValue.get("newValue"));
                default -> throw new RuntimeException("Unknown status of fieldName");
            };
            sj.add(line);
        }
        return sj.toString();
    }

    private static String getValueAsString(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return value.toString();
    }
}
