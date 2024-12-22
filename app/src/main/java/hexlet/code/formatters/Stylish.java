package hexlet.code.formatters;

import java.util.Map;
import java.util.StringJoiner;

public class Stylish {
    private static final String TEMPLATE = "  %s %s: %s";

    public static String format(Map<String, Map<String, Object>> compareResult) {
        var sj = new StringJoiner("\n");

        for (var item : compareResult.entrySet()) {
            var fieldName = item.getKey();
            var fieldValue = item.getValue();
            var fieldStatus = fieldValue.get("status").toString();

            var line = switch (fieldStatus) {
                case "removed" -> String.format(TEMPLATE, "-", fieldName, fieldValue.get("oldValue"));
                case "added" -> String.format(TEMPLATE, "+", fieldName, fieldValue.get("newValue"));
                case "without change" -> String.format(TEMPLATE, " ", fieldName, fieldValue.get("oldValue"));
                case "changed" -> String.format(TEMPLATE, "-", fieldName, fieldValue.get("oldValue")) + "\n"
                        + String.format(TEMPLATE, "+", fieldName, fieldValue.get("newValue"));
                default -> throw new RuntimeException("Unknown status of fieldName");
            };
            sj.add(line);
        }
        return "{\n" + sj + "\n}";
    }
}
