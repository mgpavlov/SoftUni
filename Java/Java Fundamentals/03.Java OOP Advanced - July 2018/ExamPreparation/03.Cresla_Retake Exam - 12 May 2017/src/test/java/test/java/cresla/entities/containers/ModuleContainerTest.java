package test.java.cresla.entities.containers;

import cresla.entities.containers.ModuleContainer;
import cresla.entities.modules.absorberModules.CooldownSystem;
import cresla.entities.modules.absorberModules.HeatProcessor;
import cresla.entities.modules.energyModules.CryogenRod;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ModuleContainerTest {
    private ModuleContainer moduleContainer;
    private EnergyModule energyModule;
    private AbsorbingModule absorbingModule1;
    private AbsorbingModule absorbingModule2;
    @Before
    public void setUp() throws Exception {
        this.moduleContainer = new ModuleContainer(2);
        Class<? extends ModuleContainer> moduleContainerClass = moduleContainer.getClass();
        Field[] declaredFields = moduleContainerClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
        }
        Method[] declaredMethods = moduleContainerClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            declaredMethod.setAccessible(true);
        }
        this.energyModule = new CryogenRod(10);

        this.absorbingModule1 = new CooldownSystem(10);
        this.absorbingModule2 = new HeatProcessor(5);
    }

    @Test
    public void addEnergyModule() {
    }

    @Test
    public void addAbsorbingModule() {
    }

    @Test
    public void getTotalEnergyOutput() {
    }

    @Test
    public void getTotalHeatAbsorbing() {
    }
}