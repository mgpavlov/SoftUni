package hell.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface HeroFactory {
    Hero create(String name, String type) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException;
}
