import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p14DragonArmy {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Map<String, Map<String, String[]>> data = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(" ");
            String type = input[0];
            String name = input[1];

            String damage = input[2].equals("null") ? "45" : input[2];
            String health = input[3].equals("null") ? "250" : input[3];
            String armor = input[4].equals("null") ? "10" : input[4];

            data.putIfAbsent(type, new TreeMap<>());
            data.get(type).put(name, new String[3]);
            data.get(type).get(name)[0] = damage;
            data.get(type).get(name)[1] = health;
            data.get(type).get(name)[2] = armor;
        }

        data.forEach((key, value) -> {
            OptionalDouble damage = value.entrySet().stream().mapToDouble(x-> Double.parseDouble(x.getValue()[0])).average();
            OptionalDouble health = value.entrySet().stream().mapToDouble(x-> Double.parseDouble(x.getValue()[1])).average();
            OptionalDouble armor = value.entrySet().stream().mapToDouble(x-> Double.parseDouble(x.getValue()[2])).average();
            System.out.printf("%s::(%.2f/%.2f/%.2f)%n", key, damage.getAsDouble(), health.getAsDouble(), armor.getAsDouble());

            value.forEach((k, v) ->{
                System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n", k, Integer.parseInt(v[0]), Integer.parseInt(v[1]), Integer.parseInt(v[2]));
            });
        });


    }
}
