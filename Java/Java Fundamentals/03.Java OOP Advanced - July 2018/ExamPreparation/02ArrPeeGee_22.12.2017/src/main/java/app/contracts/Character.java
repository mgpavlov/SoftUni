package app.contracts;

public interface Character {
    String getName();

    void setName();

    double getHealth();

    void setHealth();

    double getDamage();

    void setDamage();

    double getGold();

    void setGold();

    void levelUp();

    boolean isAlive();

}
