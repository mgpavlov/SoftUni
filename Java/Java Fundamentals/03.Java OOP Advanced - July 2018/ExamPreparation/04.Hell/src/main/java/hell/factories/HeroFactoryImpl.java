package hell.factories;

import hell.interfaces.Hero;
import hell.interfaces.HeroFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class HeroFactoryImpl implements HeroFactory {
    private static final String HERO_CLASS_PATH = "hell.entities.heroes.";

    @Override
    public Hero create(String heroName, String type) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {
        Class<?> heroClass = Class.forName(HERO_CLASS_PATH + type);
        Constructor<?> declaredConstructor = heroClass.getDeclaredConstructor();
        Hero hero = (Hero) declaredConstructor.newInstance();

        return hero;
    }
}
