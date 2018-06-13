import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class p104FootballStats {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<String>> statistics = new LinkedHashMap<>();

        while (true) {
            String input = reader.readLine();
            if (input.equals("Season End")){
                break;
            }
            List<String>  tokens = Arrays.stream(input.split("[\\s+\\-]")).filter(x->!x.isEmpty()).collect(Collectors.toList());
            String team1 = tokens.get(0);
            String team2 = tokens.get(1);
            String result1 = tokens.get(3);
            String result2 = new StringBuilder(tokens.get(3)).reverse().toString();

            statistics.putIfAbsent(team1, new ArrayList<>());
            statistics.get(team1).add(team1 + " - " + team2 + " -> " + result1);
            statistics.putIfAbsent(team2, new ArrayList<>());
            statistics.get(team2).add(team2+" - "+ team1 + " -> " + result2);
        }

        List<String> teamsForOutput = Arrays.stream(reader.readLine().split(", ")).filter(x->!x.isEmpty()).collect(Collectors.toList());
        for (String team : teamsForOutput) {
            statistics.get(team).stream().sorted((a,b)->{
               return a.split("\\s+")[2].compareTo(b.split("\\s+")[2]);
            }).forEach(System.out::println);
        }
    }
}
