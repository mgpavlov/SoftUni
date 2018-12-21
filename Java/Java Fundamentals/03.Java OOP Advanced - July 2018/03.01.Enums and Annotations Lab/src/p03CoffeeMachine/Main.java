package p03CoffeeMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (true) {
            String input = reader.readLine();
            if ("END".equals(input)) {
                break;
            }
            String[] commands = input.split("\\s+");
            if (commands.length == 1){
                coffeeMachine.insertCoin(commands[0]);
            }else {
                coffeeMachine.buyCoffee(commands[0], commands[1]);
            }
        }

        for (Coffee coffee : coffeeMachine.coffeesSold()) {
            System.out.println(coffee.toString());
        }
    }
}
