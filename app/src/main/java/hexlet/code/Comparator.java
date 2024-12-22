package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import java.util.Set;

public class Comparator {
    public static List<Map<ComparatorKeys, Object>> compare(Map<String, Object> map1, Map<String, Object> map2) {
        var result = new ArrayList<Map<ComparatorKeys, Object>>();
        var fields = makeSetOfKeys(map1, map2);

        for (var field : fields) {
            var firstMapContainsField = map1.containsKey(field);
            var secondMapContainsField = map2.containsKey(field);
            var value = new HashMap<ComparatorKeys, Object>(Map.of(ComparatorKeys.FIELD, field));

            if (firstMapContainsField && !secondMapContainsField) {
                value.put(ComparatorKeys.STATUS, ComparatorStatuses.REMOVED);
                value.put(ComparatorKeys.OLD_VALUE, map1.get(field));
            } else if (!firstMapContainsField && secondMapContainsField) {
                value.put(ComparatorKeys.STATUS, ComparatorStatuses.ADDED);
                value.put(ComparatorKeys.NEW_VALUE, map2.get(field));
            } else if (Objects.equals(map1.get(field), map2.get(field))) {
                value.put(ComparatorKeys.STATUS, ComparatorStatuses.UNCHANGED);
                value.put(ComparatorKeys.OLD_VALUE, map1.get(field));
            } else {
                value.put(ComparatorKeys.STATUS, ComparatorStatuses.CHANGED);
                value.put(ComparatorKeys.OLD_VALUE, map1.get(field));
                value.put(ComparatorKeys.NEW_VALUE, map2.get(field));
            }
            result.add(value);
        }
        return result;
    }

    private static Set<String> makeSetOfKeys(Map<String, Object> map1, Map<String, Object> map2) {
        var keys = new TreeSet<String>();
        keys.addAll(map1.keySet());
        keys.addAll(map2.keySet());
        return keys;
    }
}
