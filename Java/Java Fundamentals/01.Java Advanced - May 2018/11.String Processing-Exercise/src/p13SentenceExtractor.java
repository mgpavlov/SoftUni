import javafx.css.Match;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p13SentenceExtractor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String keyword = reader.readLine();
        String text = reader.readLine();

        String sentenceRegex = ".+?[.!?]";
        String keywordRegex = String.format("\\b%s\\b", keyword); //"\\b" + key + "\\b"

        Pattern pattern1 = Pattern.compile(sentenceRegex);
        Matcher matcher1 = pattern1.matcher(text);

        Pattern pattern2 = Pattern.compile(keywordRegex);


        while (matcher1.find()){
            Matcher matcher2 = pattern2.matcher( matcher1.group());
            if (matcher2.find()){
                System.out.println( matcher1.group().trim());
            }
        }


    }
}
