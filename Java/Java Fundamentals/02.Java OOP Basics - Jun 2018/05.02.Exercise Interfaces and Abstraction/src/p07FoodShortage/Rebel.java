package p07FoodShortage;

public class Rebel extends Creature implements Buyer {
    private int food;
    Rebel(String name, int age, String group) {
        super(name, age, group);
    }

    @Override
    public int getFood() {
        return this.food;
    }

    @Override
    public void buyFood() {
        this.food += 5;
    }
}
