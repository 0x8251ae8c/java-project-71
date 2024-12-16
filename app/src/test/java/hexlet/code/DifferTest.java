package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = { "json", "yml" })
    public void generate(String extension) throws Exception {
        var filepath1 = getFixturePath("file1." + extension);
        var filepath2 = getFixturePath("file2." + extension);

        var expected1 = readFixture("differStylish");
        var actual1 = Differ.generate(filepath1.toString(), filepath2.toString(), "stylish");

        assertEquals(expected1, actual1);

        var expected2 = readFixture("differPlain");
        var actual2 = Differ.generate(filepath1.toString(), filepath2.toString(), "plain");

        assertEquals(expected2, actual2);

        var expected3 = Differ.generate(filepath1.toString(), filepath2.toString(), "stylish");
        var actual3 = Differ.generate(filepath1.toString(), filepath2.toString());

        assertEquals(expected3, actual3);
    }

    @Test
    public void testJsonDiffer() throws Exception {
        var filepath1 = getFixturePath("fileForJsonFormatter1.json");
        var filepath2 = getFixturePath("fileForJsonFormatter2.json");

        var expected1 = readFixture("differJson.json");
        var actual1 = Differ.generate(filepath1.toString(), filepath2.toString(), "json");

        assertEquals(expected1, actual1);
    }
}
