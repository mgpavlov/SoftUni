package ExamPrep3.monuments;

public class AirMonument extends Monument {
    private int airAffinity;
    public AirMonument(String name, int airAffinity) {
        super(name);
        this.airAffinity = airAffinity;
    }

    @Override
    public double getAffinity() {
        return this.airAffinity;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Air");
        result.append(super.toString()).append(String.format("Air Affinity: %d", this.airAffinity)).append(System.lineSeparator());
        return result.toString();
    }
}
