package cresla.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Reactor extends Identifiable {

    long getTotalEnergyOutput();

    long getTotalHeatAbsorbing();

    int getModuleCount() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException;

    void addEnergyModule(EnergyModule energyModule);

    void addAbsorbingModule(AbsorbingModule absorbingModule);

}
