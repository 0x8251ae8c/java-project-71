package hexlet.code.formatters;

import java.util.Map;
import java.util.StringJoiner;

public class Stylish {
    public static String format(Map<String, Map<String, Object>> compareResult) {
        var sj = new StringJoiner("\n");

        for (var item : compareResult.entrySet()) {
            var fieldName = item.getKey();
            var fieldValue = item.getValue();
            var fieldStatus = fieldValue.get("status").toString();

            var line = switch (fieldStatus) {
                case "removed" -> "  - " + fieldName + ": " + getValueAsString(fieldValue, "oldValue");
                case "added" -> "  + " + fieldName + ": " + getValueAsString(fieldValue, "newValue");
                case "without change" -> "    " + fieldName + ": " + getValueAsString(fieldValue, "oldValue");
                case "changed" -> "  - " + fieldName + ": " + getValueAsString(fieldValue, "oldValue") + "\n"
                        + "  + " + fieldName + ": " + getValueAsString(fieldValue, "newValue");
                default -> throw new RuntimeException("Unknown status of fieldName");
            };
            sj.add(line);
        }
        return "{\n" + sj + "\n}";
    }

    private static String getValueAsString(Map<String, Object> map, String key) {
        var value = map.get(key);
        return value == null ? "null" : value.toString();
    }
}
