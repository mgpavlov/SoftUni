import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p08MelrahShake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine();
        String pattern = reader.readLine();

        StringBuilder patternBuilder = new StringBuilder(pattern);

        while (pattern.length() > 0 && pattern.length() < text.length()) {
            StringBuilder textBuilder = new StringBuilder(text);

            int firstIndex = textBuilder.indexOf(pattern);
            int lastIndex = textBuilder.lastIndexOf(pattern);

            if (firstIndex != -1 && lastIndex != -1 && firstIndex != lastIndex) {
                textBuilder.replace(firstIndex, firstIndex + pattern.length(), "");
                textBuilder.replace(lastIndex - pattern.length(), lastIndex, "");

                patternBuilder.deleteCharAt(pattern.length() / 2);
                pattern = patternBuilder.toString();

                text = textBuilder.toString();
                System.out.println("Shaked it.");
            } else {
                break;
            }
        }
        System.out.println("No shake.");
        System.out.println(text);

    }
}
