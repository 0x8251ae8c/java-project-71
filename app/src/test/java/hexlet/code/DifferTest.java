package hexlet.code;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;

class DifferTest {
    private static File filepath1;
    private static File filepath2;
    private static File result;

    @BeforeAll
    public static void beforeAll() {
        filepath1 = new File("./src/test/resources/filepath1.json");
        filepath2 = new File("./src/test/resources/filepath2.json");
        result = new File("./src/test/resources/result");
    }

    @Test
    public void testDiffer() throws Exception {
        String expected = Files.readString(result.toPath());
        String actual = Differ.generate(filepath1, filepath2);
        assertEquals(expected, actual);
    }
}
