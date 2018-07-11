package p10MooD3;


public class Demon extends BaseCharacter {
    private double energy;

    public Demon(String username, int level, double energy) {
        super(username, level);
        this.energy = energy;
        hash(username);
    }

    @Override
    public void hash(String username) {
        super.hashPassword = String.valueOf(username.length() * 217);
    }

    @Override
    public String toString() {
        return String.format("\"%s\" | \"%s\" -> %s%n%.1f", super.username, super.hashPassword, "Demon", super.level * this.energy);
    }
}
