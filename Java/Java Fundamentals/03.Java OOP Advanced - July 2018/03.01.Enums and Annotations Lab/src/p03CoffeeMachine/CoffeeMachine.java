package p03CoffeeMachine;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine {
    private int money;
    private List<Coffee> coffees;

    public CoffeeMachine() {
        this.coffees = new ArrayList<>();
    }

    public void buyCoffee(String size, String type) {
        int price = Enum.valueOf(CoffeeSize.class, size.toUpperCase()).getPrice();
        int quantity = Enum.valueOf(CoffeeSize.class, size.toUpperCase()).getMl();
        CoffeeSize coffeeSize = Enum.valueOf(CoffeeSize.class, size.toUpperCase());
        CoffeeType coffeeType = Enum.valueOf(CoffeeType.class, type.toUpperCase());

        Coffee coffee = new Coffee(coffeeSize, coffeeType);
        if (this.money>= price){
            coffees.add(coffee);
            this.money = 0;
        }
    }

    public void insertCoin(String coin) {
        this.money += Enum.valueOf(Coin.class, coin.toUpperCase()).getValue();
    }

    public Iterable<Coffee> coffeesSold() {
        return this.coffees;
    }

}
