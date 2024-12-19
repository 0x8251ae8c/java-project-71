package hexlet.code;

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

        var expectedStylishDiffer = readFixture("differStylish");
        var actualStylishDiffer1 = Differ.generate(filepath1.toString(), filepath2.toString(), "stylish");
        var actualStylishDiffer2 = Differ.generate(filepath1.toString(), filepath2.toString());

        assertEquals(expectedStylishDiffer, actualStylishDiffer1);
        assertEquals(expectedStylishDiffer, actualStylishDiffer2);

        var expectedPlainDiffer = readFixture("differPlain");
        var actualPlainDiffer = Differ.generate(filepath1.toString(), filepath2.toString(), "plain");

        assertEquals(expectedPlainDiffer, actualPlainDiffer);

        var expectedJsonDiffer = readFixture("differJson");
        var actualJsonDiffer = Differ.generate(filepath1.toString(), filepath2.toString(), "json");

        assertEquals(expectedJsonDiffer, actualJsonDiffer);
    }
}
