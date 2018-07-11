package ExamPrep3.monuments;

public class WaterMonument extends Monument {
    private int waterAffinity;
    public WaterMonument(String name, int waterAffinity) {
        super(name);
        this.waterAffinity = waterAffinity;
    }

    @Override
    public double getAffinity() {
        return this.waterAffinity;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Water");
        result.append(super.toString()).append(String.format("Water Affinity: %d", this.waterAffinity)).append(System.lineSeparator());
        return result.toString();
    }
}
