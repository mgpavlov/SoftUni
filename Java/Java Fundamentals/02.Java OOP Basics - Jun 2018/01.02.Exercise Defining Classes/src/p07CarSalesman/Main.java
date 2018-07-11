package p07CarSalesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Map<String, Engine> engines = new HashMap<>();
        while (n-- > 0) {
            String[] input = reader.readLine().split("\\s+");
            String model = input[0];
            int power = Integer.parseInt(input[1]);
            Engine engine;
            if (input.length == 3) {
                if (input[2].toUpperCase().equals(input[2].toLowerCase())) {
                    int displacement = Integer.parseInt(input[2]);
                    engine = new Engine(model, power, displacement);
                } else {
                    String efficiency = input[2];
                    engine = new Engine(model, power, efficiency);
                }
            } else if (input.length == 4) {
                int displacement = Integer.parseInt(input[2]);
                String efficiency = input[3];
                engine = new Engine(model, power, displacement, efficiency);
            } else {
                engine = new Engine(model, power);
            }
            engines.putIfAbsent(model, engine);
        }

        int m = Integer.parseInt(reader.readLine());
        List<Car> cars = new ArrayList<>();
        while (m-- > 0) {
            String[] input = reader.readLine().split("\\s+");
            String model = input[0];
            Engine engine = engines.get(input[1]);
            Car car;
            if (input.length == 3) {
                if (input[2].toUpperCase().equals(input[2].toLowerCase())) {
                    int weight = Integer.parseInt(input[2]);
                    car = new Car(model, engine, weight);
                } else {
                    String color = input[2];
                    car = new Car(model, engine, color);
                }
            } else if (input.length == 4) {
                int weight = Integer.parseInt(input[2]);
                String color = input[3];
                car = new Car(model, engine, weight, color);
            } else {
                car = new Car(model, engine);
            }
            cars.add(car);
        }

        StringBuilder result = new StringBuilder();



        cars.forEach(c->{
            result
                    .append(String.format("%s:%n", c.getModel()))
                    .append(String.format("%s:%n", c.getEngine().getModel()))
                    .append(String.format("Power: %s%n", c.getEngine().getPower()))
                    .append(String.format("Displacement: %s%n", c.getEngine().getDisplacement()))
                    .append(String.format("Efficiency: %s%n", c.getEngine().getEfficiency()))
                    .append(String.format("Weight: %s%n", c.getWeight()))
                    .append(String.format("Color: %s%n", c.getColor()));
        });

        System.out.println(result.toString().replaceAll("null", "n/a").replaceAll("\\b0\\b", "n/a"));

    }
}
