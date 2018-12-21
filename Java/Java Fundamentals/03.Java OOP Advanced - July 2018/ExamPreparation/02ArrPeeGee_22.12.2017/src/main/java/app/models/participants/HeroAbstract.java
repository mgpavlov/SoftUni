package app.models.participants;

import app.contracts.Hero;
import app.contracts.Targetable;
import app.models.Config;

public abstract class HeroAbstract implements Hero {
    private String name;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int level;
    private double health;
    private double damage;
    private double gold;
    private boolean isAlive;

    public HeroAbstract(String name) {
        this.name = name;
    }

    public HeroAbstract() {
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

    @Override
    public int getDexterity() {
        return this.dexterity;
    }

    @Override
    public int getIntelligence() {
        return this.intelligence;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getHealth() {
        return this.health;
                /*this.getStrength()* Config.HERO_HEALTH_MULTIPLIER;*/
    }

    @Override
    public double getDamage() {
        return this.damage;
    }

    @Override
    public double getGold() {
        return this.gold;
    }

    @Override
    public void levelUp() {
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public String attack(Targetable target) {
        return "";
    }

    @Override
    public void takeDamage(double damage) {

    }

    @Override
    public void giveReward(Targetable targetable) {

    }

    @Override
    public void receiveReward(double reward) {

    }

    private void setName(String name) {
        this.name = name;
    }


}
