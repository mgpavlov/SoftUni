package app.models.participants;

import app.contracts.Character;
import app.contracts.Targetable;

public class Boss extends BossAbstract {
    private String name;
    private double health;
    private double damage;
    private double gold;

    protected Boss(String name, double health, double damage, double gold) {
        super(name);
    }

    @Override
    public String attack(Targetable target) {
        return null;
    }

    @Override
    public void takeDamage(double damage) {

    }

    @Override
    public String getName() {
        return null;
    }


    @Override
    public double getDamage() {
        return 0;
    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public double getGold() {
        return 0;
    }


    @Override
    public void giveReward(Targetable targetable) {

    }

    @Override
    public void receiveReward(double reward) {

    }

    @Override
    public void levelUp() {

    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
