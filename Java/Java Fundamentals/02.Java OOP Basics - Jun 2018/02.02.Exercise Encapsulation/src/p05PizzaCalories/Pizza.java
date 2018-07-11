package p05PizzaCalories;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, Dough dough, List<Topping> toppings) {
        this.setName(name);
        this.setDough(dough);
        this.setToppings(toppings);
    }

    public double calculateCalories() {
        double calories = this.dough.calculateCalories() + toppings.stream().mapToDouble(Topping::calculateCalories).sum();
        return calories;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.length()>15){
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    public Dough getDough() {
        return this.dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    public List<Topping> getToppings() {
        return this.toppings;
    }

    public void setToppings(List<Topping> toppings) {
        if (toppings.size()>10){
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = toppings;
    }
}
