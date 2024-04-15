package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(File filepath) throws Exception {
        Path file = Paths.get(filepath.toURI()).toAbsolutePath().normalize();
        if (!Files.exists(file)) {
            throw new Exception("File '" + file + "' does not exist");
        }

        ObjectMapper mapper = getMapper(file);

        return mapper.readValue(Files.readString(file), new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(Path file) {
        var fileType = file.getFileName().toString().split("\\.")[1];

        return switch (fileType) {
            case "json" -> new ObjectMapper();
            case "yml" -> new YAMLMapper();
            default -> null;
        };
    }
}
