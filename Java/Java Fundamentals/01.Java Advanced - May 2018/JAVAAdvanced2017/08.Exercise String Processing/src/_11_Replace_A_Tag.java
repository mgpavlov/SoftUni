package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern; //100/100

public class _11_Replace_A_Tag {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String regex = "(<a).*(>)[^<]*(<\\/a>)";

        Pattern pattern = Pattern.compile(regex);

        StringBuilder text = new StringBuilder();
        while (!"END".equals(input)) {
            text.append(input).append("\n");
            input = reader.readLine();
        }
        Matcher matcher = pattern.matcher(text.toString());

        while (matcher.find()) {
            String replacement = matcher.group(0)
                    .replace(matcher.group(1), "[URL")
                    .replace(matcher.group(3), "[/URL]")
                    .replace(matcher.group(2), "]");
            text = new StringBuilder(text.toString().replace(matcher.group(0), replacement));
        }
        System.out.println(text);
    }
}
