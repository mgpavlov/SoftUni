import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.*;

public class _04_Highscore {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Long> playerPoints = new LinkedHashMap<>();
        Map<String, List<String>> playerOpponents = new HashMap<>();

        while (true) {
            String[] line = reader.readLine().split("\\s+");
            if("osu!".equals(line[0])) {
                break;
            }
            Integer player1Points = Integer.valueOf(line[0]);
            Integer player2Points = Integer.valueOf(line[2]);
            String[] names = line[1].split("<->");

            fillMaps(names[0], names[1], playerOpponents, playerPoints, player1Points, player2Points);
            fillMaps(names[1], names[0], playerOpponents, playerPoints, player2Points, player1Points);
        }

        StringBuilder result = new StringBuilder();
        playerPoints.entrySet().stream().sorted((a,b) -> Long.compare(b.getValue(), a.getValue()))
                .forEach(v -> {
                    result.append(v.getKey()).append(" - (").append(v.getValue()).append(")").append("\n");
                    playerOpponents.get(v.getKey()).forEach(s -> result.append(s).append("\n"));
                });
        System.out.println(result);
    }

    private static void fillMaps(String name, String name1, Map<String, List<String>> playerOpponents, Map<String, Long> playerPoints, Integer player1Points, Integer player2Points) {
        if(!playerPoints.containsKey(name)) {
            playerPoints.put(name, 0L);
        }
        playerPoints.put(name, playerPoints.get(name) + (player1Points-player2Points));
        if(!playerOpponents.containsKey(name)) {
            playerOpponents.put(name, new ArrayList<>());
        }
        playerOpponents.get(name).add("*   " + name1 + " <-> " + (player1Points-player2Points));
    }
}

