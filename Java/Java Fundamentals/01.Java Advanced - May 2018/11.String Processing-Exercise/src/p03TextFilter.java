import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p03TextFilter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] banWords = reader.readLine().split(", ");
        String  text = reader.readLine();

        for (String word : banWords) {
            StringBuilder replacer = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                replacer.append("*");
            }
            text = text.replace(word, replacer);
        }

        System.out.println(text);
    }
}
