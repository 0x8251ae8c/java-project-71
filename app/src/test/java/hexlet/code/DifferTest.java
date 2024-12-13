package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @Test
    public void generate() throws Exception {
        var filepath1 = getFixturePath("file1.json");
        var filepath2 = getFixturePath("file2.json");

        var expected = readFixture("differ");
        var actual = Differ.generate(filepath1.toString(), filepath2.toString());

        assertEquals(expected, actual);
    }
}
