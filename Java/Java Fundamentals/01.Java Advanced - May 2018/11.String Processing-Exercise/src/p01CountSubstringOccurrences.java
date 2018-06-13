import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p01CountSubstringOccurrences {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine().toLowerCase();
        String  str = reader.readLine().toLowerCase();

        int counter = 0;
        int startIndex = text.indexOf(str);
        while (startIndex != -1){
            counter++;
            startIndex = text.indexOf(str, startIndex+1);
        }
        System.out.println(counter);
    }
}
