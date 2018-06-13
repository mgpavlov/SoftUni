import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p103Ascent {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> encodedStrings = new LinkedHashMap<>();

        String regex = "(_|,)([A-Za-z]+)([0-9])";
        //String regex = "[_,]*(([_,])([A-Za-z]+)([0-9]))[0-9]*";
        //String regex = "[_,]*(([_,])([A-Za-z]+)([0-9]))[0-9]*";
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            String input = reader.readLine();
            if ("Ascend".equals(input)) {
                break;
            }
            for (String key : encodedStrings.keySet()) {
                if (input.contains(key)){
                    input = input.replaceAll(key, encodedStrings.get(key));
                }
            }
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String word = matcher.group(2);
                String sign = matcher.group(1);
                int number = Integer.parseInt(matcher.group(3));

                String encodedString = EncodeMatch(word, sign, number);
                encodedStrings.putIfAbsent(matcher.group(0), encodedString);
                input = input.replaceFirst(matcher.group(0), encodedString);
            }
            System.out.println(input.trim());
        }
    }

    private static String EncodeMatch(String word, String sign, int number) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (sign.startsWith("_")) {
                result.append((char) (word.charAt(i) - number));
            } else {
                result.append((char) (word.charAt(i) + number));
            }
        }
        return String.valueOf(result);
    }
}
