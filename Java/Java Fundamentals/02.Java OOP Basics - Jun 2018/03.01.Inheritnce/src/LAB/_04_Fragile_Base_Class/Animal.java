package _03_Inheritance.LAB._04_Fragile_Base_Class;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    protected List<Food> foodEaten;

    public Animal() {
        this.foodEaten = new ArrayList<>();
    }

    protected final void eat(Food food) {
        foodEaten.add(food);
    }

    public void eatAll(Food[] foods) {
        for (Food food : foods) {
            eat(food);
        }
    }
}
