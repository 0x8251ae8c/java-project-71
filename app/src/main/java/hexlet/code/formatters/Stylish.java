package hexlet.code.formatters;

import hexlet.code.ComparatorKeys;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static hexlet.code.ComparatorStatuses.ADDED;
import static hexlet.code.ComparatorStatuses.REMOVED;
import static hexlet.code.ComparatorStatuses.CHANGED;
import static hexlet.code.ComparatorStatuses.UNCHANGED;

public class Stylish {
    private static final String TEMPLATE = "  %s %s: %s";

    public static String format(List<Map<ComparatorKeys, Object>> compareResult) {
        var sj = new StringJoiner("\n");

        for (var map : compareResult) {
            var field = map.get(ComparatorKeys.FIELD);
            var status = map.get(ComparatorKeys.STATUS);

            var line = switch (status) {
                case REMOVED -> String.format(TEMPLATE, "-", field, map.get(ComparatorKeys.OLD_VALUE));
                case ADDED -> String.format(TEMPLATE, "+", field, map.get(ComparatorKeys.NEW_VALUE));
                case UNCHANGED -> String.format(TEMPLATE, " ", field, map.get(ComparatorKeys.OLD_VALUE));
                case CHANGED -> String.format(TEMPLATE, "-", field, map.get(ComparatorKeys.OLD_VALUE)) + "\n"
                        + String.format(TEMPLATE, "+", field, map.get(ComparatorKeys.NEW_VALUE));
                default -> throw new RuntimeException("Unknown status of fieldName");
            };
            sj.add(line);
        }
        return "{\n" + sj + "\n}";
    }
}
