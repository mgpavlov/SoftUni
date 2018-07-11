import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class _03_Copy_Bytes {
        public static void main(String[] args) {
            String inputPath = "C:\\Users\\me\\Documents\\JAVA - Advanced - септември 2017\\17.INPUTOUTPUT, FILES AND DIRECTORIES\\src\\input2.txt";
            String outputPath = "C:\\Users\\me\\Documents\\JAVA - Advanced - септември 2017\\17.INPUTOUTPUT, FILES AND DIRECTORIES\\src\\output.txt";

            try (FileInputStream fis = new FileInputStream(inputPath);
                 FileOutputStream fos = new FileOutputStream(outputPath)) {

                int oneByte = fis.read();

                while (oneByte >= 0) {
                    if (oneByte == ' ' || oneByte == '\n') {
                        System.out.print(oneByte);
                        fos.write(oneByte);
                    } else {
                        String oneByteString = String.valueOf(oneByte);
                        for (char ch : oneByteString.toCharArray()) {
                            System.out.print(ch);
                            fos.write(ch);
                        }
                    }
                    oneByte = fis.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
