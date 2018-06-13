import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p12ExtractEmails {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        //String regex = "(?<=\\s|^)[a-zA-Z\\d][\\w-.]+?[a-zA-Z\\d]@[a-zA-Z][a-zA-Z-.]*?(\\.[a-zA-Z]+)+(?=\\.|,|\\s|$)";
        //String regex = "(?<=(\\s|^))([a-z0-9][-._]*)+[a-z0-9]@(([a-z][-]*)+[a-z]\\.)+[a-z]+\\b";
        String regex = "(?<=(\\s|^))([a-z0-9][-._]*)+[a-z0-9]@(([a-z][-]*)+[a-z]\\.)+[a-z]+(?=\\.|,|\\s|$)";

        Pattern emailPattern = Pattern.compile(regex);
        Matcher matcher = emailPattern.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
        }
    }
}
