package ExamPrep3.benders;

public class FireBender extends Bender {
    private double heatAggression;
    public FireBender(String name, int power, double heatAggression) {
        super(name, power);
        this.heatAggression = heatAggression;
    }
    @Override
    protected double getBenderPower() {
        return super.getPower()*this.heatAggression;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Fire");
        result.append(super.toString()).append(String.format("Heat Aggression: %.2f", this.heatAggression)).append(System.lineSeparator());
        return result.toString();
    }
}
