package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class Stylish {
    public static String format(List<Map<String, Object>> compareResult) {
        var sj = new StringJoiner("\n");

        for (var item : compareResult) {
            var field = item.get("field").toString();
            var status = item.get("status").toString();
            var value = item.get("value").toString();

            switch (status) {
                case "removed":
                    sj.add("  - " + field + ": " + value);
                    break;
                case "added":
                    sj.add("  + " + field + ": " + value);
                    break;
                case "without change":
                    sj.add("    " + field + ": " + value);
                    break;
                case "changed":
                    var newValue = item.get("newValue").toString();
                    sj.add("  - " + field + ": " + value);
                    sj.add("  + " + field + ": " + newValue);
                    break;
                default:
                    throw new RuntimeException("Unknown status of field");
            }
        }
        return "{\n" + sj + "\n}";
    }
}
