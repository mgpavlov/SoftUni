package app.models.participants;

import app.constants.Config;
import app.contracts.Targetable;

public class Wizard extends AbstractHero {

    public Wizard() {
        super(Config.WIZARD_BASE_STRENGTH, Config.WIZARD_BASE_DEXTERITY, Config.WIZARD_BASE_INTELLIGENCE);
    }

    public Wizard(String name) {
        this();
        this.setName(name);
    }

    @Override
    public double getDamage() {
        return (this.getIntelligence() * Config.WIZARD_DAMAGE_MULTIPLIER) + this.getDexterity();
    }

    @Override
    public String attack(Targetable target) {
        super.activateSpeciality();
        return super.attack(target);
    }
}
