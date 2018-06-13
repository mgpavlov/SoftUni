import javafx.css.Match;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p09MatchFullName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String pattern = "^([A-Z][a-z]+) [A-Z][a-z]+$";

        while (true){
            String input = reader.readLine();
            if ("end".equals(input)){
                break;
            }
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(input);

            if (matcher.find()){
                System.out.println(input);
            }
        }
    }
}
