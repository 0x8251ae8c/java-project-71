package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Comparator {
    public static Map<String, Map<String, Object>> compare(Map<String, Object> map1, Map<String, Object> map2) {
        var result = new TreeMap<String, Map<String, Object>>();
        var keys = makeSetOfKeys(map1, map2);

        for (var key : keys) {
            var firstMapContainsKey = map1.containsKey(key);
            var secondMapContainsKey = map2.containsKey(key);
            var value = new LinkedHashMap<String, Object>();

            var oldValue = map1.getOrDefault(key, null);
            var newValue = map2.getOrDefault(key, null);

            if (firstMapContainsKey && !secondMapContainsKey) {
                value.put("status", "removed");
                value.put("oldValue", oldValue);
            } else if (!firstMapContainsKey && secondMapContainsKey) {
                value.put("status", "added");
                value.put("newValue", newValue);
            } else {
                if (oldValue == null) {
                    oldValue = "null";
                }
                if (newValue == null) {
                    newValue = "null";
                }
                if (oldValue.equals(newValue)) {
                    value.put("status", "without change");
                    value.put("oldValue", oldValue);
                } else {
                    value.put("status", "changed");
                    value.put("oldValue", oldValue);
                    value.put("newValue", newValue);
                }
            }
            result.put(key, value);
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
