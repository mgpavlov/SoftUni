package hell.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface CommandController {
    void createHero(String heroName, String heroType) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException;

    void createCommonItem(String itemName, String heroName, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitpointsBonus, int damageBonus);

    void createRecipeItem (String itemName, String heroName, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitpointsBonus, int damageBonus, String... requiredItems);

    void inspectHero(String heroName);

    void quit();
}
