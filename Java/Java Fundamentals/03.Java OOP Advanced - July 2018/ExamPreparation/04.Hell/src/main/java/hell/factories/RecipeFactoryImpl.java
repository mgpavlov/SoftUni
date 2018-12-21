package hell.factories;

import hell.interfaces.Recipe;
import hell.interfaces.RecipeFactory;

public class RecipeFactoryImpl implements RecipeFactory{


    @Override
    public Recipe create(String itemName, String heroName, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitpointsBonus, int damageBonus, String... requiredItems) {
        return null;
    }
}
