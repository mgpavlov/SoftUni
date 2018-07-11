package p05PizzaCalories;

public class Dough {
    private static final int DEFAULT_CALORIES_PER_GRAM = 2;
    private String type;
    private String bakingTechnique;
    private double weight;

    public Dough(String type, String bakingTechnique, double weight) {
        this.setType(type);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    public double calculateCalories() {
        double calories = weight * DEFAULT_CALORIES_PER_GRAM *this.getModifiersType()*getModifiersBake();
        return calories;
    }

    private void setType(String type) {
        if (!type.equalsIgnoreCase("white") && !type.equalsIgnoreCase("wholegrain")) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.type = type;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!bakingTechnique.equalsIgnoreCase("crispy") && !bakingTechnique.equalsIgnoreCase("chewy") && !bakingTechnique.equalsIgnoreCase("homemade")) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight > 200 || weight < 1) {
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    private double getModifiersType() {
        switch (this.type.toLowerCase()) {
            case "white":
                return 1.50;
            case "wholegrain":
                return 1.00;
                default:
                    return 1;
        }
    }
    private double getModifiersBake() {
        switch (this.bakingTechnique.toLowerCase()){
            case "crispy":
                return 0.90;
            case "chewy":
                return 1.1;
            case "homemade":
                return 1.0;
            default:
                return 1;
        }
    }
}
