import java.io.BufferedReader;//100/100
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p07MapsDistricts2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Integer>> citiesDistricts = new LinkedHashMap<>();

        String[] input = reader.readLine().split("\\s+");

        int givenPopulation = Integer.parseInt(reader.readLine());
        for (String city : input) {
            String[]tokens = city.split(":");
            String cityName = tokens[0];
            int population = Integer.parseInt(tokens[1]);
            citiesDistricts.putIfAbsent(cityName, new ArrayList<>());
            citiesDistricts.get(cityName).add(population);
        }

        citiesDistricts.entrySet().stream()
                .filter(x->x.getValue().stream().reduce((a,b)->a+b).get() > givenPopulation)
                .sorted((a,b)->{
                    int z1 = a.getValue().stream().reduce((c,d)->c+d).get();
                    int z2 = b.getValue().stream().reduce((c,d)->c+d).get();
                    return Integer.compare(z2, z1);
                }).forEach(c ->{
            System.out.printf("%s: ",c.getKey());
            c.getValue().stream().sorted((a, b)-> Integer.compare(b, a))
                    .limit(5).forEach(city->{
                System.out.printf("%d ", city);
            });
            System.out.println();
        });
    }
}
