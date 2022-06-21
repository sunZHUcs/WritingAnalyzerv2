import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileParser {

    public static Map<Integer, String> files = new LinkedHashMap<>();

    public static void parseFiles() {

        File dir = new File("input");
        File[] infoGrabber = dir.listFiles();
        final int numFiles = dir.list().length;

        for (int i = 0; i < numFiles; i++) {
            files.put(i, infoGrabber[i].getAbsolutePath());
        }
    }

    public static File getFile(int i) {
        return new File(files.get(i));
    }

    public static String readFile(File file) throws IOException {
        return Files.readString(Paths.get(String.valueOf(file)));
    }
}
