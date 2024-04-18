package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static String filepath1;
    private static String filepath2;
    private static String differStylish;
    private static String differPlain;
    private static String differJson;

    @BeforeAll
    public static void beforeAll() {
        differStylish = "./src/test/resources/differStylish";
        differPlain = "./src/test/resources/differPlain";
        differJson = "./src/test/resources/differJson";
    }

    @Test
    public void testDifferJson() throws Exception {
        filepath1 = "./src/test/resources/filepath1.json";
        filepath2 = "./src/test/resources/filepath2.json";

        testStylish(filepath1, filepath2);
        testPlain(filepath1, filepath2);
        testJson(filepath1, filepath2);
    }

    @Test
    public void testDifferYaml() throws Exception {
        filepath1 = "./src/test/resources/filepath1.yml";
        filepath2 = "./src/test/resources/filepath2.yml";

        testStylish(filepath1, filepath2);
        testPlain(filepath1, filepath2);
        testJson(filepath1, filepath2);
    }

    private void testStylish(String fp1, String fp2) throws Exception {
        String expected = Files.readString(Path.of(differStylish));
        String actual1 = Differ.generate(fp1, fp2);
        String actual2 = Differ.generate(fp1, fp2, "stylish");

        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    private void testPlain(String fp1, String fp2) throws Exception {
        String expected = Files.readString(Path.of(differPlain));
        String actual = Differ.generate(fp1, fp2, "plain");

        assertEquals(expected, actual);
    }

    private void testJson(String fp1, String fp2) throws Exception {
        String expected = Files.readString(Path.of(differJson));
        String actual = Differ.generate(fp1, fp2, "json");

        assertEquals(expected, actual);
    }
}
