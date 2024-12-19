package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Objects;

public class Comparator {
    public static Map<String, Map<String, Object>> compare(Map<String, Object> map1, Map<String, Object> map2) {
        var result = new TreeMap<String, Map<String, Object>>();
        var fields = makeSetOfKeys(map1, map2);

        for (var field : fields) {
            var firstMapContainsField = map1.containsKey(field);
            var secondMapContainsField = map2.containsKey(field);
            var value = new LinkedHashMap<String, Object>();

            if (firstMapContainsField && !secondMapContainsField) {
                value.put("status", "removed");
                value.put("oldValue", map1.get(field));
            } else if (!firstMapContainsField && secondMapContainsField) {
                value.put("status", "added");
                value.put("newValue", map2.get(field));
            } else if (Objects.equals(map1.get(field), map2.get(field))) {
                value.put("status", "without change");
                value.put("oldValue", map1.get(field));
            } else {
                value.put("status", "changed");
                value.put("oldValue", map1.get(field));
                value.put("newValue", map2.get(field));
            }

            result.put(field, value);
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
