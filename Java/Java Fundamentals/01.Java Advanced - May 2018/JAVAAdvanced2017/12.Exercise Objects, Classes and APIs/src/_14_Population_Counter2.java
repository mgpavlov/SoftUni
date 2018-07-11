import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class _14_Population_Counter2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, LinkedHashMap<String, Long>> contries = new LinkedHashMap();

        while (true) {
            String[] line = reader.readLine().split("\\|");
            if ("report".equalsIgnoreCase(line[0])) {
                break;
            }
            if (!contries.containsKey(line[1])) {
                contries.put(line[1], new LinkedHashMap<>());
                contries.get(line[1]).put(line[0], Long.valueOf(0L));
            }
            contries.get(line[1]).put(line[0], Long.valueOf(line[2]));
        }

        HashMap<String, Long> contriespopulation = new LinkedHashMap<>();

        for (String country : contries.keySet()) {
            LinkedHashMap<String, Long> sortedTowns =

                    contries.get(country).entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

            Long sum = 0L;
            for (String s : sortedTowns.keySet()) {
                sum += sortedTowns.get(s);
            }
            contriespopulation.put(country, sum);
        }
        contriespopulation.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .forEach(b -> {
                    System.out.println(b.getKey() + " (total population: " + b.getValue() + ")");
                    contries.get(b.getKey()).entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).forEach(a -> System.out.println("=>" + a.getKey() + ": " + a.getValue()));
                });
    }
}
