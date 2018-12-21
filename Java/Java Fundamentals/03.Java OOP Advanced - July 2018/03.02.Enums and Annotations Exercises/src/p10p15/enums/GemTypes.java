package p10p15.enums;

public enum GemTypes {
    RUBY(7, 2, 5), EMERALD(1, 4, 9), AMETHYST(2, 8, 4);
    int strength;
    int agility;
    int vitality;

    GemTypes(int strength, int agility, int vitality) {
        this.setStrength(strength);
        this.setAgility(agility);
        this.setVitality(vitality);
    }

    public int getStrength() {
        return this.strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return this.agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getVitality() {
        return this.vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }
}
