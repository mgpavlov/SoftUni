import java.io.BufferedReader;//100/100
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p15ValidUsernames {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String regex = "(?<=[/\\\\()\\s+]|^)[a-zA-Z][\\w]{2,24}(?=[/\\\\() \\s+]|$)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<String> username = new ArrayList<>();

        while (matcher.find()) {
            username.add(matcher.group());
        }
        int biggestSum = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < username.size() - 1; i++) {
            int currentSum = username.get(i).length() + username.get(i + 1).length();

            if (currentSum > biggestSum) {
                biggestSum = currentSum;
                index = i;
            }
        }

        System.out.println(username.get(index));
        System.out.println(username.get(index + 1));
    }
}
