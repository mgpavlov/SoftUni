package p03AnimalFarm;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    private void setAge(int age) {
        if (age > 15 || age < 0) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    private void setName(String name) {
        if (name.length() < 1 || name.equals(" ")) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    private double calculateProductPerDay() {
        double product;
        if (this.age < 6) {
            product = 2;
        } else if (this.age < 12) {
            product = 1;
        } else {
            product = 0.75;
        }
        return product;
    }

    public double productPerDay() {
        return calculateProductPerDay();
    }
}
