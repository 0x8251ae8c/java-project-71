package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {
    public static List<Map<String, Object>> compare(Map<String, Object> map1, Map<String, Object> map2) {
        var result = new ArrayList<Map<String, Object>>();
        var keys = makeSetOfKeys(map1, map2);

        for (var key : keys) {
            var firstMapContainsKey = map1.containsKey(key);
            var secondMapContainsKey = map2.containsKey(key);
            var item = new HashMap<String, Object>();

            item.put("field", key);

            var oldValue = map1.getOrDefault(key, null);
            var newValue = map2.getOrDefault(key, null);

            if (firstMapContainsKey && !secondMapContainsKey) {
                item.put("status", "removed");
                item.put("value", oldValue);
            } else if (!firstMapContainsKey && secondMapContainsKey) {
                item.put("status", "added");
                item.put("value", newValue);
            } else {
                if (oldValue == null) {
                    oldValue = "null";
                }
                if (newValue == null) {
                    newValue = "null";
                }
                if (oldValue.equals(newValue)) {
                    item.put("status", "without change");
                    item.put("value", oldValue);
                } else {
                    item.put("status", "changed");
                    item.put("value", oldValue);
                    item.put("newValue", newValue);
                }
            }
            result.add(item);
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
