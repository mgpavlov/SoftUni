import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class _06_Sort_Lines {
    public static void main(String[] args) {
        Path inputPath = Paths.get("C:\\Users\\me\\Documents\\JAVA - Advanced - септември 2017\\17.INPUTOUTPUT, FILES AND DIRECTORIES\\src\\input.txt");
        Path outputPath = Paths.get("C:\\Users\\me\\Documents\\JAVA - Advanced - септември 2017\\17.INPUTOUTPUT, FILES AND DIRECTORIES\\src\\output.txt");

        try {
            List<String> lines = Files.readAllLines(inputPath);
            Collections.sort(lines);
            Files.write(outputPath, lines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
