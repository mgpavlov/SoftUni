package ExamPrep3.monuments;

public class FireMonument extends Monument {
    private int fireAffinity;
    public FireMonument(String name, int fireAffinity) {
        super(name);
        this.fireAffinity = fireAffinity;
    }

    @Override
    public double getAffinity() {
        return this.fireAffinity;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Fire");
        result.append(super.toString()).append(String.format("Fire Affinity: %d", this.fireAffinity)).append(System.lineSeparator());
        return result.toString();
    }
}
