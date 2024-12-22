package hexlet.code.formatters;

import hexlet.code.ComparatorKeys;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static hexlet.code.ComparatorStatuses.ADDED;
import static hexlet.code.ComparatorStatuses.REMOVED;
import static hexlet.code.ComparatorStatuses.CHANGED;
import static hexlet.code.ComparatorStatuses.UNCHANGED;

public class Plain {
    private static final String TEMPLATE_REMOVED = "Property '%s' was removed";
    private static final String TEMPLATE_ADDED = "Property '%s' was added with value: %s";
    private static final String TEMPLATE_CHANGED = "Property '%s' was updated. From %s to %s";

    public static String format(List<Map<ComparatorKeys, Object>> compareResult) {
        var sj = new StringJoiner("\n");

        for (var map : compareResult) {
            var field = map.get(ComparatorKeys.FIELD);
            var status = map.get(ComparatorKeys.STATUS);

            if (status.equals(UNCHANGED)) {
                continue;
            }

            var line = switch (status) {
                case REMOVED -> String.format(TEMPLATE_REMOVED, field);
                case ADDED -> String.format(TEMPLATE_ADDED, field,
                        getValueAsString(map.get(ComparatorKeys.NEW_VALUE)));
                case CHANGED -> String.format(TEMPLATE_CHANGED, field,
                        getValueAsString(map.get(ComparatorKeys.OLD_VALUE)),
                        getValueAsString(map.get(ComparatorKeys.NEW_VALUE)));
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
