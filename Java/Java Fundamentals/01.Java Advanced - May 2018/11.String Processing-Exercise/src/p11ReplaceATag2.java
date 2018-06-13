import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p11ReplaceATag2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pattern = "<a(.+?)>(.*?)</a>";

        StringBuilder code = new StringBuilder();

        while (true) {
            String line = scanner.nextLine();
            if ("END".equals(line)) {
                break;
            }
            code.append(line).append("\n");
        }

        Pattern regex = Pattern.compile(pattern,Pattern.DOTALL);
        Matcher matcher = regex.matcher(code);

        while (matcher.find(0)) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            String replacement = "[URL" + matcher.group(1) + "]" + matcher.group(2) + "[/URL]";
            code.replace(startIndex, endIndex, replacement);
        }
        System.out.println(code.toString());
    }
}
