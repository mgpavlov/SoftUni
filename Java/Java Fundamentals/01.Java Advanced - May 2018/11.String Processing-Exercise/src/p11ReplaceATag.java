import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p11ReplaceATag {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder text = new StringBuilder();
        while (true) {
            String input = reader.readLine();
            if ("END".equals(input)) {
                break;
            }
            text.append(input).append("\n");
        }

        String regex = "(<a).*(>)[^<]*(</a>)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String replacer = matcher.group(0)
                    .replace(matcher.group(1), "[URL")
                    .replace(matcher.group(3), "[/URL]")
                    .replace(matcher.group(2), "]");

            text = new StringBuilder(text.toString().replace(matcher.group(0), replacer));
        }
        System.out.println(text);


    }
}
