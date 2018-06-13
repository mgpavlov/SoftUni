import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class p10PopulationCounter {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Map<String, Long>> popInfo = new LinkedHashMap<>();
        Map<String, Long> totalCountryPopulation = new LinkedHashMap<>();

        while (true) {
            String input = reader.readLine();
            if ("report".equals(input)) {
                break;
            }

            String[] tokens = input.split("\\|");
            String city = tokens[0];
            String country = tokens[1];
            long population = Long.parseLong(tokens[2]);

            //popInfo.putIfAbsent(country, new LinkedHashMap<>())

            if (!popInfo.containsKey(country)) {
                popInfo.put(country, new LinkedHashMap<>());
            }
            popInfo.get(country).put(city, population);


            if (!totalCountryPopulation.containsKey(country)) {
                totalCountryPopulation.put(country, 0L);
            }
            totalCountryPopulation.put(country, totalCountryPopulation.get(country) + population);
        }

        popInfo.entrySet()
                .stream()
                .sorted((a, b) -> {
                    Long population1 = totalCountryPopulation.get(a.getKey());
                    Long population2 = totalCountryPopulation.get(b.getKey());

                    return Long.compare(population2, population1);
                })
                .forEach(country -> {
                    System.out.printf("%s (total population: %d)%n", country.getKey(), totalCountryPopulation.get(country.getKey()));
                    country.getValue().entrySet().stream().sorted((a, b) -> {
                        Long population1 = a.getValue();
                        Long population2 = b.getValue();
                        return Long.compare(population2, population1);
                    }).forEach(city -> {
                        System.out.printf("=>%s: %d%n", city.getKey(), city.getValue());
                    });
                });
    }
}
