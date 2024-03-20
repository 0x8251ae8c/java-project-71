package hexlet.code;

import java.io.File;

public class Differ {
    public static String generate(File filepath1, File filepath2) {
        String result = "";
        result += "Call Differ.generate()? Here it is!\n";
        result += "  First arg = '" + filepath1 + "' \n";
        result += "  Second arg = '" + filepath2 + "' \n";
        return result;
    }
}
