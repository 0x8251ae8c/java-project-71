package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Plain {
    private static final String TEMPLATE_REMOVED = "Property '%s' was removed";
    private static final String TEMPLATE_ADDED = "Property '%s' was added with value: %s";
    private static final String TEMPLATE_CHANGED = "Property '%s' was updated. From %s to %s";

    public static String format(Map<String, Map<String, Object>> compareResult) {
        var sj = new StringJoiner("\n");

        for (var item : compareResult.entrySet()) {
            var fieldName = item.getKey();
            var fieldValue = item.getValue();
            var fieldStatus = fieldValue.get("status").toString();

            if (fieldStatus.equals("without change")) {
                continue;
            }

            var line = switch (fieldStatus) {
                case "removed" -> String.format(TEMPLATE_REMOVED, fieldName);
                case "added" -> String.format(TEMPLATE_ADDED, fieldName,
                        getValueAsString(fieldValue.get("newValue")));
                case "changed" -> String.format(TEMPLATE_CHANGED, fieldName,
                        getValueAsString(fieldValue.get("oldValue")),
                        getValueAsString(fieldValue.get("newValue")));
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
