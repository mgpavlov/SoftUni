package hell.interfaces;

public interface RecipeFactory {
    Recipe create(String itemName, String heroName, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitpointsBonus, int damageBonus, String...requiredItems);
}
