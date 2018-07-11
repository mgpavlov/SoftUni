package p07FoodShortage;

class Citizen extends Creature implements Buyer {
    private int food;
    protected Citizen(String name, int age, String id, String birthdate) {
        super(name, age, id, birthdate);
    }

    @Override
    public void buyFood() {
        this.food += 10;
    }

    @Override
    public int getFood() {
        return this.food;
    }
}
