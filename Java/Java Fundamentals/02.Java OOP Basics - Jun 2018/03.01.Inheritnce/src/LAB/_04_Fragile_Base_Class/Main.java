package _03_Inheritance.LAB._04_Fragile_Base_Class;

public class Main {
    public static void main(String[] args) {
        Predator predator = new Predator();

        predator.feed(new Food());

        System.out.println();
    }
}
