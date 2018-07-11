package ExamPrep3.benders;

public class WaterBender extends Bender {
    private double waterClarity;
    public WaterBender(String name, int power, double waterClarity) {
        super(name, power);
        this.waterClarity = waterClarity;
    }
    @Override
    protected double getBenderPower() {
        return super.getPower()*this.waterClarity;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Water");
        result.append(super.toString()).append(String.format("Water Clarity: %.2f", this.waterClarity)).append(System.lineSeparator());
        return result.toString();
    }
}
