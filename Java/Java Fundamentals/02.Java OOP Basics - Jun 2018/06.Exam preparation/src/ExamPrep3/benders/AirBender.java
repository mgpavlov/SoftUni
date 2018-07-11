package ExamPrep3.benders;

public class AirBender extends Bender {
    private double aerialIntegrity;
    public AirBender(String name, int power, double aerialIntegrity) {
        super(name, power);
        this.aerialIntegrity = aerialIntegrity;
    }

    @Override
    protected double getBenderPower() {
        return super.getPower()*this.aerialIntegrity;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Air");
        result.append(super.toString()).append(String.format("Aerial Integrity: %.2f", this.aerialIntegrity)).append(System.lineSeparator());
        return result.toString();
    }
}
