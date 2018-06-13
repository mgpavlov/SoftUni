import java.io.BufferedReader;//100/100
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p14SumOfAllValues {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String keyString = reader.readLine();
        String textString = reader.readLine();
        String startKey = "";
        String endKey = "";
        /*String startKeyRegex = "^[a-zA-Z_]+(?=\\d)";
        String endKeyRegex = "(?<=\\d)[a-zA-Z_]+$";*/
        String keysRegex = "(^[a-zA-Z_]+(?=\\d)).*((?<=\\d)[a-zA-Z_]+$)";

       /* if (!Pattern.matches(startKeyRegex, keyString)) {
            System.out.println("<p>A key is missing</p>");
            return;
        }
        if (!Pattern.matches(endKeyRegex, keyString)) {
            System.out.println("<p>A key is missing</p>");
            return;
        }*/

        /*Pattern patternStartKey = Pattern.compile(startKeyRegex);
        Pattern patternEndKey = Pattern.compile(endKeyRegex);

        Matcher matcherStart = patternStartKey.matcher(keyString);
        Matcher matcherEnd = patternEndKey.matcher(keyString);*/

        Pattern patternKeys = Pattern.compile(keysRegex);
        Matcher matcherKeys = patternKeys.matcher(keyString);

        if (matcherKeys.find()){
            startKey = matcherKeys.group(1);
            endKey = matcherKeys.group(2);
        }else {
            System.out.println("<p>A key is missing</p>");
            return;
        }

        String regex = startKey + ".*?" + endKey;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(textString);

        String numbersRegex = "[0-9.]+";
        Pattern numbersPattern = Pattern.compile(numbersRegex);

        double totalValue = 0.0;

        while(matcher.find()){
            Matcher numbersMatcher = numbersPattern.matcher(matcher.group(0));
            while (numbersMatcher.find()){
                try {
                    totalValue += Double.parseDouble(numbersMatcher.group(0));
                }catch (Exception ignored){
                }
            }
        }
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(totalValue > 0 ? "<p>The total value is: <em>" + df.format(totalValue) + "</em></p>" : "<p>The total value is: <em>nothing</em></p>");
    }
}