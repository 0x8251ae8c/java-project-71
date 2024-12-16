package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Json {
    public static String format(Map<String, Map<String, Object>> compareResult) throws Exception {
        return new ObjectMapper().writeValueAsString(compareResult);
    }
}
