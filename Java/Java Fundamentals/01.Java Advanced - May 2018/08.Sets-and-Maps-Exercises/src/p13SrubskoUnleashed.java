import java.io.BufferedReader; //90/100
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class p13SrubskoUnleashed {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map <String, Map<String, BigDecimal>> data = new LinkedHashMap<>();

        while (true){
            String input = reader.readLine();
            if ("End".equals(input)){
                break;
            }
            String[] tokens = input.split(" @");
            if (tokens.length<2){
                continue;
            }
            String singer = tokens[0].trim();

            String[] info = tokens[1].split("\\s+");
            if (info.length < 3){
                continue;
            }
            int ticketsPrice = Integer.parseInt(info[info.length-2]);
            int ticketsCount = Integer.parseInt(info[info.length-1]);

            String venue = tokens[1].substring(0, tokens[1].indexOf(ticketsPrice+"")).trim();

            data.putIfAbsent(venue, new LinkedHashMap<>());
            data.get(venue).putIfAbsent(singer, new BigDecimal(0));

            data.get(venue).put(singer, data.get(venue).get(singer).add(new BigDecimal(ticketsPrice*ticketsCount)));
        }

        data.entrySet().stream().forEach(v->{
            System.out.println(v.getKey());
            v.getValue().entrySet().stream().sorted((a,b)->{
              return b.getValue().compareTo(a.getValue());
            }).forEach(s->{
                System.out.printf("#  %s -> %.0f%n", s.getKey(), s.getValue());
            });
        });
    }
}
