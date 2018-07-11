package p10MooD3;

public class Archangel extends BaseCharacter{
    private int mana;

    public Archangel(String username, int level, int mana) {
        super(username, level);
        this.mana = mana;
        hash(username);
    }

    @Override
    public void hash(String username) {
        StringBuilder sb = new StringBuilder(username);
        sb.reverse().append(username.length() * 21);
        super.hashPassword = sb.toString();
    }

    @Override
    public String toString() {
        return String.format("\"%s\" | \"%s\" -> %s%n%d", super.username, super.hashPassword, "Archangel", super.level * this.mana);
    }
}
