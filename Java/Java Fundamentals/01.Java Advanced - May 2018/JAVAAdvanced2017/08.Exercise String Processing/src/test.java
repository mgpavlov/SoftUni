package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static void main(String[] args) throws IOException {

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        String regex = "<a href=\\\".+\\\">([\\s\\S]+?<\\/a>)?";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        StringBuilder text = new StringBuilder();

        while (true) {
            String input = scan.readLine();
            if ("END".equals(input))
            {
                break;
            }
            text.append(input).append("\n");
        }

        Matcher matcher = pattern.matcher(text.toString());
        while (matcher.find()) {
            String replacement = matcher.group(0)
                    .replace("<a", "[URL")
                    .replace("</a>", "[/URL]")
                    .replace(">", "]");
            text = new StringBuilder(text.toString().replace(matcher.group(0), replacement));
        }
        System.out.println(text);
    }
}
