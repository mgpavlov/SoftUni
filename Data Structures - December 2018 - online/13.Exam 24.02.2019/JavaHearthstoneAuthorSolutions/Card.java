public class Card {

    private String name;
    private int score;
    private int damage;
    private int health;
    private int level;

    public Card(String name, int damage, int score, int level) {
        this.name = name;
        this.score = score;
        this.damage = damage;
        this.level = level;
        this.health = 20;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Card other = ((Card) obj);
        if (this == other) return true;

        return this.getScore() == other.getScore() &&
                this.getName().equals(other.getName()) &&
                this.getLevel() == other.getLevel() &&
                this.health == other.getHealth() &&
                this.getDamage() == this.getDamage();
    }
}
