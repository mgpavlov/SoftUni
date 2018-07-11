package p04FragileBaseClass;

public class Predator extends Animal{
    private int health;
    public void feed(Food food){

    }

    @Override
    public void eat(Food food) {
        super.eat(food);
    }
}
