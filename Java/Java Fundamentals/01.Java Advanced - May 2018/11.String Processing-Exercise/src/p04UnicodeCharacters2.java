import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p04UnicodeCharacters2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            stringBuilder.
                    append("\\u")
                    .append(Integer.toHexString(text.charAt(i) | 0x10000).substring(1));
        }

        System.out.println(stringBuilder.toString());
    }
}
