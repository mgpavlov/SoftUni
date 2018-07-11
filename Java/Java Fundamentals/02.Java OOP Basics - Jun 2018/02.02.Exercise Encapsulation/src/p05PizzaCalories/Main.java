package p05PizzaCalories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] pizzaInfo = reader.readLine().split("\\s+");
        String pizzaName = pizzaInfo[1];
        int numberOfToppings = Integer.parseInt(pizzaInfo[2]);
        if (numberOfToppings>10){
            System.out.println("Number of toppings should be in range [0..10].");
            return;
        }

        String[] doughInfo = reader.readLine().split("\\s+");
        String flourType = doughInfo[1];
        String bakingTechnique = doughInfo[2];
        int flourWeight = Integer.parseInt(doughInfo[3]);
        Dough dough = null;
        try {
            dough = new Dough(flourType,bakingTechnique, flourWeight);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return;
        }


        List<Topping>toppings = new ArrayList<>(numberOfToppings);
        while (true){
            String[] toppingsInfo = reader.readLine().split("\\s+");
            if (toppingsInfo[0].equals("END")){
                break;
            }
            String toppingType = toppingsInfo[1];
            int toppingWeight = Integer.parseInt(toppingsInfo[2]);
            Topping topping = null;
            try{
                topping = new Topping(toppingType, toppingWeight);
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getMessage());
                return;
            }
            toppings.add(topping);
        }

        Pizza pizza = null;
        try{
            pizza = new Pizza(pizzaName, dough, toppings);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            return;
        }

        System.out.printf("%s - %.2f", pizzaName, pizza.calculateCalories());
    }
}
