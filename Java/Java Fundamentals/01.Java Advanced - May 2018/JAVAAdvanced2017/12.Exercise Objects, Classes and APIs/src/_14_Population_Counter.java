import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.util.*;
import java.util.stream.Collectors;
public class _14_Population_Counter {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, LinkedHashMap<String, Integer>> countryAndCityPopulation = new LinkedHashMap<>();
        Map<String, Long> countryPopulation = new LinkedHashMap<>();

        enterCountriesAndPopulation(bf, countryAndCityPopulation, countryPopulation);

        LinkedHashMap<String, Long> orderedByCountryPopulation = countryPopulation.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<String, Long> country : orderedByCountryPopulation.entrySet()) {
            System.out.printf("%s (total population: %d)%n", country.getKey(), country.getValue());

            LinkedHashMap<String, Integer> orderedCitiesByPopulation =
                    countryAndCityPopulation.get(country.getKey())
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            for (Map.Entry<String, Integer> city : orderedCitiesByPopulation.entrySet()) {
                System.out.printf("=>%s: %d%n", city.getKey(), city.getValue());
            }
        }
    }

    private static void enterCountriesAndPopulation(BufferedReader bf,
                                                    Map<String, LinkedHashMap<String, Integer>> countryAndCityPopulation,
                                                    Map<String, Long> countryPopulation) throws IOException {
        while (true) {
            String[] line = bf.readLine().split("\\|");

            if ("report".equalsIgnoreCase(line[0])) {
                break;
            }

            String city = line[0];
            String country = line[1];
            int population = Integer.valueOf(line[2]);

            if (!countryAndCityPopulation.containsKey(country)) {
                LinkedHashMap<String, Integer> cityPopulation = new LinkedHashMap<>();

                countryAndCityPopulation.put(country, cityPopulation);
                countryPopulation.put(country, 0L);
            }

            LinkedHashMap<String, Integer> citiesPopulation = countryAndCityPopulation.get(country);
            citiesPopulation.put(city, population);
            countryAndCityPopulation.put(country, citiesPopulation);
            long totalCountryPopulation = countryPopulation.get(country) + population;
            countryPopulation.put(country, totalCountryPopulation);
        }
    }
}
