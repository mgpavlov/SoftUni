package p03CoffeeMachine;

public class Coffee {
    private CoffeeSize size;
    private CoffeeType type;

    public Coffee(CoffeeSize size, CoffeeType type) {
        this.size = size;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.size.toString() + " " + this.type.toString();
    }
}
