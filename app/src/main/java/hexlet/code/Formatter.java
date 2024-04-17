package hexlet.code;

import java.util.Map;

public class Formatter {
    public static String generate(Map<String, Object> map1,
                                  Map<String, Object> map2,
                                  Map<String, String> diff,
                                  String format) {
        StringBuilder sb = new StringBuilder();

        for (var key : diff.keySet()) {
            String valueState = diff.get(key);

            switch (valueState) {
                case "removed":
                    sb.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                    break;
                case "added":
                    sb.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
                    break;
                case "kept":
                    sb.append("    ").append(key).append(": ").append(map1.get(key)).append("\n");
                    break;
                case "changed":
                    sb.append("  - ").append(key).append(": ").append(map1.get(key)).append("\n");
                    sb.append("  + ").append(key).append(": ").append(map2.get(key)).append("\n");
                    break;
                default:
                    sb.append("  ? unknown diff state\n");
            }
        }

        return "{\n" + sb + "}";
    }
}
