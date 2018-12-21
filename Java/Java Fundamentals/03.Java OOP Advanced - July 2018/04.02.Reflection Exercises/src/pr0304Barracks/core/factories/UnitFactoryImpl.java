package pr0304Barracks.core.factories;

import jdk.jshell.spi.ExecutionControl;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "pr0304Barracks.models.units.";

    @Override
    public Unit createUnit(String unitType) {
        Class<?> unitClass = null;
        Unit unit = null;
        try {
            unitClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
            Constructor<?> constructor = unitClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            unit = (Unit) constructor.newInstance();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return unit;
        /*throw new ExecutionControl.NotImplementedException("message");*/
    }
}
