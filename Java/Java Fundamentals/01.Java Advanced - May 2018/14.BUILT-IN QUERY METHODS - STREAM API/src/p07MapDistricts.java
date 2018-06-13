import java.io.BufferedReader;//80/100
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p07MapDistricts {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Map<String, Integer> citiesTotal = new LinkedHashMap<>();
            Map<String, Set<Integer>> citiesDistricts = new LinkedHashMap<>();

            String[] input = reader.readLine().split("\\s+");

            int givenPopulation = Integer.parseInt(reader.readLine());
            for (String city : input) {
                String[]tokens = city.split(":");
                String cityName = tokens[0];
                int population = Integer.parseInt(tokens[1]);
                citiesDistricts.putIfAbsent(cityName, new TreeSet<>());
                citiesDistricts.get(cityName).add(population);

                citiesTotal.putIfAbsent(cityName, 0);
                citiesTotal.put(cityName, citiesTotal.get(cityName) + population);
            }
            /*for (int i = 0; i < input.length; i += 2) {
                if (input[i].equals("")) continue;
                citiesDistricts.putIfAbsent(input[i], new TreeSet<>());
                citiesDistricts.get(input[i]).add(Integer.parseInt(input[i + 1]));

                citiesTotal.putIfAbsent(input[i], 0);
                citiesTotal.put(input[i], citiesTotal.get(input[i]) + Integer.parseInt(input[i + 1]));
            }*/

            citiesTotal.entrySet().stream().sorted((a, b) -> {
                return b.getValue().compareTo(a.getValue());
            }).filter(x -> x.getValue() > givenPopulation).forEach(c -> {
                System.out.printf("%s: ", c.getKey());
                citiesDistricts.get(c.getKey()).stream().sorted((a, b) -> {
                    return Integer.compare(b, a);
                }).limit(5).forEach(x -> {
                    System.out.print(x + " ");
                });
                System.out.println();
            });
        } catch (Exception ignored) {

        }

    }
}
