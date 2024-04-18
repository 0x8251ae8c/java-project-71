package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferTest {
    private static File filepath1;
    private static File filepath2;
    private static File differStylish;
    private static File differPlain;
    private static File differJson;

    @BeforeAll
    public static void beforeAll() {
        differStylish = new File("./src/test/resources/differStylish");
        differPlain = new File("./src/test/resources/differPlain");
        differJson = new File("./src/test/resources/differJson");
    }

    @Test
    public void testDifferJson() throws Exception {
        filepath1 = new File("./src/test/resources/filepath1.json");
        filepath2 = new File("./src/test/resources/filepath2.json");

        testStylish(filepath1, filepath2);
        testPlain(filepath1, filepath2);
        testJson(filepath1, filepath2);
    }

    @Test
    public void testDifferYaml() throws Exception {
        filepath1 = new File("./src/test/resources/filepath1.yml");
        filepath2 = new File("./src/test/resources/filepath2.yml");

        testStylish(filepath1, filepath2);
        testPlain(filepath1, filepath2);
        testJson(filepath1, filepath2);
    }

    private void testStylish(File fp1, File fp2) throws Exception {
        String expected = Files.readString(differStylish.toPath());
        String actual1 = Differ.generate(fp1, fp2);
        String actual2 = Differ.generate(fp1, fp2, "stylish");

        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    private void testPlain(File fp1, File fp2) throws Exception {
        String expected = Files.readString(differPlain.toPath());
        String actual = Differ.generate(fp1, fp2, "plain");

        assertEquals(expected, actual);
    }

    private void testJson(File fp1, File fp2) throws Exception {
        String expected = Files.readString(differJson.toPath());
        String actual = Differ.generate(fp1, fp2, "json");

        assertEquals(expected, actual);
    }
}
