package p07FoodShortage;

public abstract class Creature implements Identificatable, Birthdayable, Buyer {
    private String name;
    private int age;
    private String id;
    private String birthdate;
    private String group;
    private int food;

    @Override
    public void buyFood() {
        this.food += 10;
    }

    public int getFood() {
        return this.food;
    }

    public Creature(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
    }

    Creature(String name, int age, String id, String birthdate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthdate = birthdate;
    }

    Creature(String name, String birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }


    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }
}
