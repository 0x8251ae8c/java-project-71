package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    static Map<String, String> parseToMap(String filepath) throws Exception {
        var fileContent = readFile(filepath);
        var fileType = filepath.split("\\.")[1];

        return switch (fileType) {
            case ("json") -> new ObjectMapper().readValue(fileContent, new TypeReference<>() { });
            case ("yaml"), ("yml") -> new YAMLMapper().readValue(fileContent, new TypeReference<>() { });
            default -> null;
        };
    }

    private static String readFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path);
    }
}
