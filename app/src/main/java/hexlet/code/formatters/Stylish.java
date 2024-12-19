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
                case "removed" -> "  - " + fieldName + ": " + fieldValue.get("oldValue");
                case "added" -> "  + " + fieldName + ": " + fieldValue.get("newValue");
                case "without change" -> "    " + fieldName + ": " + fieldValue.get("oldValue");
                case "changed" -> "  - " + fieldName + ": " + fieldValue.get("oldValue") + "\n"
                        + "  + " + fieldName + ": " + fieldValue.get("newValue");
                default -> throw new RuntimeException("Unknown status of fieldName");
            };
            sj.add(line);
        }
        return "{\n" + sj + "\n}";
    }
}
