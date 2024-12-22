package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String expectedStylishDiffer;
    private static String expectedPlainDiffer;
    private static String expectedJsonDiffer;
    private static ObjectMapper mapper;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylishDiffer = readFixture("differStylish");
        expectedPlainDiffer = readFixture("differPlain");
        expectedJsonDiffer = readFixture("differJson");
        mapper = new ObjectMapper();
    }

    @ParameterizedTest
    @ValueSource(strings = { "json", "yml" })
    public void generate(String extension) throws Exception {
        var filepath1 = getFixturePath("file1." + extension).toString();
        var filepath2 = getFixturePath("file2." + extension).toString();

        var actualStylishDiffer1 = Differ.generate(filepath1, filepath2, "stylish");
        var actualStylishDiffer2 = Differ.generate(filepath1, filepath2);

        assertEquals(expectedStylishDiffer, actualStylishDiffer1);
        assertEquals(expectedStylishDiffer, actualStylishDiffer2);

        var actualPlainDiffer = Differ.generate(filepath1, filepath2, "plain");

        assertEquals(expectedPlainDiffer, actualPlainDiffer);

        var actualJsonDiffer = Differ.generate(filepath1, filepath2, "json");

        var mapExpected = mapper.readValue(expectedJsonDiffer, new TypeReference<>() { });
        var mapActual = mapper.readValue(actualJsonDiffer, new TypeReference<>() { });

        assertEquals(mapExpected, mapActual);
    }
}
