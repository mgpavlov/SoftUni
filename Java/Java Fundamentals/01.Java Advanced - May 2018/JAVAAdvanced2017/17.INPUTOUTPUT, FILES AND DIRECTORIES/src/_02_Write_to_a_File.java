import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class _02_Write_to_a_File {
    public static void main(String[] args) {
        String inputPath = "C:\\Users\\me\\Documents\\JAVA - Advanced - септември 2017\\17.INPUTOUTPUT, FILES AND DIRECTORIES\\src\\input2.txt";
        String outputPath = "C:\\Users\\me\\Documents\\JAVA - Advanced - септември 2017\\17.INPUTOUTPUT, FILES AND DIRECTORIES\\src\\output.txt";
        Set<Character> punctuation = new HashSet<>();
        Collections.addAll(punctuation, '.', ',', '!', '?');

        try (FileInputStream fis = new FileInputStream(inputPath);
             FileOutputStream fos = new FileOutputStream(outputPath)) {

            int oneByte = fis.read();

            while (oneByte >= 0) {
                if (!punctuation.contains((char) oneByte)) {
                    fos.write(oneByte);
                }

                oneByte = fis.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

