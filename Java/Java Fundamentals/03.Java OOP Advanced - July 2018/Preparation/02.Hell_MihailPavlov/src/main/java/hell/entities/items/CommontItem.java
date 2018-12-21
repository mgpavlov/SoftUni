package hell.entities.items;

import hell.interfaces.Item;

public class CommontItem extends AbstractItem {
    public CommontItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus) {
        super(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
    }
}
