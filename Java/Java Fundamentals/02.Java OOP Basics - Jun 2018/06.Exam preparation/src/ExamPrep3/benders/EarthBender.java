package ExamPrep3.benders;

public class EarthBender extends Bender {
    private double groundSaturation;
    public EarthBender(String name, int power, double groundSaturation) {
        super(name, power);
        this.groundSaturation = groundSaturation;
    }
    @Override
    protected double getBenderPower() {
        return super.getPower()*this.groundSaturation;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Earth");
        result.append(super.toString()).append(String.format("Ground Saturation: %.2f", this.groundSaturation)).append(System.lineSeparator());
        return result.toString();
    }
}
