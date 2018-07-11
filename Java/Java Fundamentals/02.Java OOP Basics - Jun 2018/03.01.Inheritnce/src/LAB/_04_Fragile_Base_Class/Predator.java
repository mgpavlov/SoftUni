package _03_Inheritance.LAB._04_Fragile_Base_Class;

import java.util.ArrayList;

public class Predator extends Animal {
    private int health;

    public void feed(Food food) {
        super.eat(food);
        this.health++;
    }
}
