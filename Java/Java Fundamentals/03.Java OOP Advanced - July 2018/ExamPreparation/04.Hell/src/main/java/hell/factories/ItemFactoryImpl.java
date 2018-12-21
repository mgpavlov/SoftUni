package hell.factories;

import hell.entities.items.CommonItem;
import hell.interfaces.Item;
import hell.interfaces.ItemFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ItemFactoryImpl implements ItemFactory {
    private static final String ITEM_CLASS_PATH = "hell.entities.items.";
    private static final String ITEM_CLASS_NAME_SUFFIX = "Item";

    @Override
    public Item create(String itemName, String heroName, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitpointsBonus, int damageBonus) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> itemClass = Class.forName(ITEM_CLASS_PATH + itemName + ITEM_CLASS_NAME_SUFFIX);
        Constructor<?> declaredConstructor = itemClass.getDeclaredConstructor();
        CommonItem item = (CommonItem) declaredConstructor.newInstance();

        return item;

    }
}
