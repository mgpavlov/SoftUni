package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _13_Sentence_Extractor2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String key = reader.readLine();
        String text = reader.readLine();

        String pattern = "[A-Z].+?[.!?]";
        String patternKey = "\\b" + key + "\\b";

        Pattern regex = Pattern.compile(pattern);
        Pattern regexKey = Pattern.compile(patternKey);

        List<String> sentences = new ArrayList<>();

        Matcher matcher = regex.matcher(text);
        while (matcher.find()) {
            sentences.add(matcher.group());
        }

        for (String sentence : sentences) {
            Matcher keyMatcher = regexKey.matcher(sentence);
            if (keyMatcher.find()) {
                System.out.println(sentence);
            }
        }
    }
}
