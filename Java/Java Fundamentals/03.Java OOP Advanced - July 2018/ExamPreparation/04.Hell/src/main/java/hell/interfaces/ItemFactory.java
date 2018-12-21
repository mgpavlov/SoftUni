package hell.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface ItemFactory {
    Item create(String itemName, String heroName, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitpointsBonus, int damageBonus) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
