package app.contracts;

public interface Targetable {

    String attack(Targetable target);

    void takeDamage(double damage);

    void giveReward(Targetable targetable);

    void receiveReward(double reward);

}
