package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var fileContent1 = readFile(filepath1);
        var fileContent2 = readFile(filepath2);

        var fileType1 = getFileType(filepath1);
        var fileType2 = getFileType(filepath2);

        var map1 = Parser.parseToMap(fileContent1, fileType1);
        var map2 = Parser.parseToMap(fileContent2, fileType2);

        var compareResult = Comparator.compare(map1, map2);

        return Formatter.format(compareResult, format);
    }

    private static String readFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        return Files.readString(path);
    }

    private static String getFileType(String filepath) {
        return filepath.split("\\.")[1];
    }
}
