import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class exam4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> contestPass = new LinkedHashMap<>();

        Map<String, Map<String, Integer>> data = new TreeMap<>();
        while (true) {
            String input = reader.readLine();
            if ("end of contests".equals(input)) {
                break;
            }
            String[] tokens = input.split(":");
            if (tokens.length!=2){
                continue;
            }
            String contest = tokens[0].trim();
            String pass = tokens[1].trim();
            contestPass.putIfAbsent(contest, pass);
        }
        while (true) {
            String input = reader.readLine();
            if ("end of submissions".equals(input)) {
                break;
            }
            String[] tokens = input.split("=>");
            String contest = tokens[0].trim();
            String pass = tokens[1].trim();
            String username = tokens[2].trim();
            int points = Integer.parseInt(tokens[3].trim());

            if (contestPass.containsKey(contest)) {
                if (pass.equals(contestPass.get(contest))) {
                    data.putIfAbsent(username, new TreeMap<>());
                    data.get(username).putIfAbsent(contest, points);
                    if (points > data.get(username).get(contest)) {
                        data.get(username).put(contest, points);
                    }
                }
            }
        }
        int maxPoints = data.entrySet().stream()
                .mapToInt(u -> u.getValue().entrySet().stream().mapToInt(e -> e.getValue()).sum()).max().getAsInt();

        data.entrySet().stream().sorted((a, b) -> {
            return Integer.compare(b.getValue().entrySet().stream().mapToInt(e -> e.getValue()).sum(), a.getValue().entrySet().stream().mapToInt(e -> e.getValue()).sum());
        }).limit(1).forEach(x -> {
            System.out.printf("Best candidate is %s with total %d points.%n", x.getKey(), maxPoints);
        });
        System.out.println("Ranking:");

        data.entrySet().stream().forEach(s -> {
            System.out.println(s.getKey());
            s.getValue().entrySet().stream().sorted((a, b) -> {
                return b.getValue().compareTo(a.getValue());
            }).forEach(e -> {
                System.out.printf("#  %s -> %d%n", e.getKey(), e.getValue());
            });
        });
    }
}
