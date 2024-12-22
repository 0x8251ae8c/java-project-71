package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.ComparatorKeys;

import java.util.List;
import java.util.Map;

public class Json {
    public static String format(List<Map<ComparatorKeys, Object>> compareResult) throws Exception {
        return new ObjectMapper().writeValueAsString(compareResult);
    }
}
