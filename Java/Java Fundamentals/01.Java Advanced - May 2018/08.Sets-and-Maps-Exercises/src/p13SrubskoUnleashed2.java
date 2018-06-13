import java.io.BufferedReader; //100/100
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p13SrubskoUnleashed2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map <String, Map<String, BigDecimal>> data = new LinkedHashMap<>();

        while (true){
            String input = reader.readLine();
            if ("End".equals(input)){
                break;
            }

            String regex = "(.*?)\\s@(.*?)\\s(\\d+)\\s(\\d+)";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            String singer = "";
            String venue = "";
            int ticketsPrice = 0;
            int ticketsCount = 0;
            if (matcher.find()){
                singer = matcher.group(1);
                venue = matcher.group(2);
                ticketsPrice = Integer.parseInt(matcher.group(3));
                ticketsCount = Integer.parseInt(matcher.group(4));
            }else{
                continue;
            }

            data.putIfAbsent(venue, new LinkedHashMap<>());
            data.get(venue).putIfAbsent(singer, new BigDecimal(0));

            data.get(venue).put(singer, data.get(venue).get(singer).add(new BigDecimal(ticketsPrice*ticketsCount)));
        }

        data.forEach((key, value) -> {
            System.out.println(key);
            value.entrySet().stream().sorted((a, b) -> {
                return b.getValue().compareTo(a.getValue());
            }).forEach(s -> {
                System.out.printf("#  %s -> %.0f%n", s.getKey(), s.getValue());
            });
        });
    }
}
