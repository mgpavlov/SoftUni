package app.models.participants;

import app.contracts.Character;
import app.contracts.Targetable;
import app.models.Config;

import java.lang.annotation.Target;

public abstract class BossAbstract implements Character, Targetable {
    private String name;
    private double health;
    private double damage;
    private double gold;

    protected BossAbstract(String name) {
        this.setName(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getHealth() {
        return Config.BOSS_HEALTH;
    }

    @Override
    public double getDamage() {
        return Config.BOSS_DAMAGE;
    }

    @Override
    public double getGold() {
        return Config.BOSS_GOLD;
    }

    @Override
    public void levelUp() {
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public String attack(Targetable target) {
        return null;
    }

    @Override
    public void takeDamage(double damage) {
    }

    @Override
    public void giveReward(Targetable targetable) {
        targetable.receiveReward(Config.BOSS_INDIVIDUAL_REWARD);
    }

    @Override
    public void receiveReward(double reward) {
    }

    private void setName(String name) {
        this.name = name;
    }
}
