package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _16_Extract_Hyperlinks2 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder text = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line.equals("END")) {
                break;
            }

            text.append(line);
        }
        String pattern = "(?<=<a)\\s*[^>]*?\\s*[^>]*?href\\s*=\\s*(\"*)('*)([^>]+?[a-z:/.]*)\\2\\1\\s*[^>]*?\\s*(?=>)";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group(3));
        }
    }
}
