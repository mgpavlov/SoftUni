package p07FoodShortage;

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
        Map<String, Creature> creatures = new HashMap<>();
        int n = Integer.parseInt(reader.readLine());

        while (n-- > 0){
            String[] input = reader.readLine().split("\\s+");

            if (input.length == 4){
                creatures.put(input[0], new Citizen(input[0], Integer.parseInt(input[1]), input[2], input[3]));
            }else {
                creatures.put(input[0], new Rebel(input[0], Integer.parseInt(input[1]), input[2]));
            }
        }
        while (true) {
            String input = reader.readLine();
            if ("End".equals(input)) {
                break;
            }

            if (creatures.containsKey(input)){
                creatures.get(input).buyFood();
            }
        }

        System.out.println(creatures.entrySet().stream().mapToInt(c -> c.getValue().getFood()).sum());
    }
}
