package ExamPrep3.monuments;

public class EarthMonument extends Monument {
    private int earthAffinity;
    public EarthMonument(String name, int earthAffinity) {
        super(name);
        this.earthAffinity = earthAffinity;
    }

    @Override
    public double getAffinity() {
        return this.earthAffinity;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Earth");
        result.append(super.toString()).append(String.format("Earth Affinity: %d", this.earthAffinity)).append(System.lineSeparator());
        return result.toString();
    }
}
