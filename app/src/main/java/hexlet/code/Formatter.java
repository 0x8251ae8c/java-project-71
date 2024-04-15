package hexlet.code;

public class Formatter {
    private final StringBuffer sb;

    public Formatter() {
        sb = new StringBuffer();
    }

    public void insertValue(String key, String value) {
        format("  + ", key, value);
    }

    public void removeValue(String key, String value) {
        format("  - ", key, value);
    }

    public void changeValue(String key, String value1, String value2) {
        format("  - ", key, value1);
        format("  + ", key, value2);
    }

    public void keepValue(String key, String value) {
        format("    ", key, value);
    }

    private void format(String action, String key, String value) {
        sb.append(action)
                .append(key)
                .append(": ")
                .append(value)
                .append("\n");
    }

    @Override
    public String toString() {
        return "{\n" + sb + "}";
    }
}
