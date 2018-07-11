import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class _04_ExtractIntegers {
    public static void main(String[] args) {
        String inputPath = "C:\\Users\\me\\Documents\\JAVA - Advanced - септември 2017\\17.INPUTOUTPUT, FILES AND DIRECTORIES\\src\\input.txt";
        String outputPath = "C:\\Users\\me\\Documents\\JAVA - Advanced - септември 2017\\17.INPUTOUTPUT, FILES AND DIRECTORIES\\src\\output.txt";

        try (Scanner scanner = new Scanner(new FileInputStream(inputPath));
             PrintWriter writer = new PrintWriter(new FileOutputStream(outputPath))) {

            while (scanner.hasNext()){
                if (scanner.hasNextInt()){
                    writer.println(scanner.nextInt());
                }

                scanner.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
