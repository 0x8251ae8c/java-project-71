package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2, Map<String, String> diff) {
        StringBuilder sb = new StringBuilder();

        for (var key : diff.keySet()) {
            String valueState = diff.get(key);

            if (!sb.isEmpty() && !valueState.equals("kept")) {
                sb.append("\n");
            }

            switch (valueState) {
                case "removed":
                    sb.append("Property '")
                            .append(key)
                            .append("' was removed");
                    break;
                case "added":
                    sb.append("Property '")
                            .append(key)
                            .append("' was added with value: ")
                            .append(valueToString(map2.get(key)));
                    break;
                case "kept":
                    break;
                case "changed":
                    sb.append("Property '")
                            .append(key)
                            .append("' was updated. From ")
                            .append(valueToString(map1.get(key)))
                            .append(" to ")
                            .append(valueToString(map2.get(key)));
                    break;
                default:
                    sb.append("Unknown diff state");
            }
        }

        return sb.toString();
    }

    private static String valueToString(Object value) {
        if (value == null) {
            return "null";
        }

        if (value instanceof Boolean) {
            return value.toString();
        }

        if (value instanceof Integer) {
            return value.toString();
        }

        if (value instanceof String) {
            return "'" + value + "'";
        }

        return "[complex value]";
    }
}
