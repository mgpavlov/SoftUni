package ExamPrep3.monuments;

public abstract class Monument {
    private String name;

    public Monument(String name) {
        this.name = name;
    }
    public abstract double getAffinity();

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format(" Monument: %s, ", this.name);
    }
}
