package p05PizzaCalories;

public class Topping {
    private static final int DEFAULT_CALORIES_PER_GRAM = 2;
    private String type;
    private double weight;

    public double calculateCalories() {
        double calories = weight * DEFAULT_CALORIES_PER_GRAM * this.getModifiers();
        return calories;
    }

    public Topping(String type, double weight) {
        this.setType(type);
        this.setWeight(weight);
    }

    public void setType(String type) {
        if (!type.equalsIgnoreCase("meat")&& !type.equalsIgnoreCase("veggies")&& !type.equalsIgnoreCase("cheese") && !type.equalsIgnoreCase("sauce")){
            throw new IllegalArgumentException("Cannot place "+type+" on top of your pizza.");
        }
        this.type = type;
    }

    public void setWeight(double weight) {
        if (weight>50||weight<1){
            throw new IllegalArgumentException(type+" weight should be in the range [1..50].");
        }
        this.weight = weight;
    }
    private double getModifiers() {
        switch (this.type.toLowerCase()) {
            case "meat":
                return 1.20;
            case "veggies":
                return 0.80;
            case "cheese":
                return 1.10;
            case "sauce":
                return 0.90;
            default:
                return 1;
        }
    }
}
/*The toppings can be of type meat, veggies, cheese or sauce. */