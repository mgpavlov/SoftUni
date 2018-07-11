package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;   // 100/100
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _16_Extract_Hyperlinks {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder text = new StringBuilder();
        Pattern patern = Pattern.compile("<a\\s+([^>]+)?href\\s*=\\s*('([^']*)'|\"([^\"]*)\"|([^\\s>]+))[^>]*>");

        while (true) {
            String line = reader.readLine();
            if ("END".equals(line)) {
                break;
            }
            text.append(line);
        }
        Matcher matcher = patern.matcher(text);

        while (matcher.find()) {
            for (int i = 5; i >= 0; i--) {
                String result = matcher.group(i);
                if (result != null) {
                    System.out.println(result);
                    break;
                }
            }
        }
    }
}
