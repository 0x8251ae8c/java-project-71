package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public class Parser {
    static Map<String, Object> parseToMap(String fileContent, String fileType) throws Exception {
        return switch (fileType) {
            case ("json") -> new ObjectMapper().readValue(fileContent, new TypeReference<>() { });
            case ("yaml"), ("yml") -> new YAMLMapper().readValue(fileContent, new TypeReference<>() { });
            default -> throw new RuntimeException("unknown file type");
        };
    }
}
