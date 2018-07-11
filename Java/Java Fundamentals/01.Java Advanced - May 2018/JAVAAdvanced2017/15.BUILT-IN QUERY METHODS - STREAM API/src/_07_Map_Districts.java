import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _07_Map_Districts {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        List<String> tokens = Stream.of(bf.readLine().split("\\s+")).collect(Collectors.toList());
        int limit = Integer.valueOf(bf.readLine());
        Map<String, List<Integer>> cities = new HashMap<>();

        for (String token : tokens) {
            String[] tokenArgs = token.split(":");
            String cityName = tokenArgs[0];
            int districtPopulation = Integer.valueOf(tokenArgs[1]);

            cities.putIfAbsent(cityName, new ArrayList<>());
            cities.get(cityName).add(districtPopulation);
        }

        cities.entrySet().stream()
                .filter(filterCitiesByTotalPopulation(limit))
                .sorted(orderCitiesDescendingByTotalPopulation())
                .forEach(printTopFiveDistrictsByCity());
    }

    private static Consumer<Map.Entry<String, List<Integer>>> printTopFiveDistrictsByCity() {
        return c -> {
            System.out.printf("%s: ", c.getKey());

            c.getValue().stream()
                    .sorted((p1, p2) -> Integer.compare(p2, p1))
                    .limit(5)
                    .forEach(p -> System.out.printf("%d ", p));
            System.out.println();
        };
    }

    private static Comparator<Map.Entry<String, List<Integer>>> orderCitiesDescendingByTotalPopulation() {
        return (c1, c2) -> Integer.compare(c2.getValue().stream().mapToInt(Integer::valueOf).sum(),
                c1.getValue().stream().mapToInt(Integer::valueOf).sum());
    }

    private static Predicate<Map.Entry<String, List<Integer>>> filterCitiesByTotalPopulation(int limit) {
        return c -> c.getValue().stream().mapToInt(Integer::valueOf).sum() >= limit;
    }
}
