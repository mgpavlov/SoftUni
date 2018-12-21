package hell.entities.items;

import hell.interfaces.Item;

public abstract class AbstractItem implements Item {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getStrengthBonus() {
        return 0;
    }

    @Override
    public int getAgilityBonus() {
        return 0;
    }

    @Override
    public int getIntelligenceBonus() {
        return 0;
    }

    @Override
    public int getHitPointsBonus() {
        return 0;
    }

    @Override
    public int getDamageBonus() {
        return 0;
    }
}
