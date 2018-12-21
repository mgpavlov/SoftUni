package cresla.entities.containers;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Module;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.Map;

public class ModuleContainerTest {

    private Container container;
    private EnergyModule energy1;
    private EnergyModule energy2;
    private AbsorbingModule absrobing1;
    private AbsorbingModule absrobing2;

    @Before
    public void setUp() throws Exception {
        this.container = new ModuleContainer(5);

        this.energy1 = Mockito.mock(EnergyModule.class);
        this.energy2 = Mockito.mock(EnergyModule.class);

        this.absrobing1 = Mockito.mock(AbsorbingModule.class);
        this.absrobing2 = Mockito.mock(AbsorbingModule.class);

        Mockito.when(absrobing1.getId()).thenReturn(1);
        Mockito.when(absrobing2.getId()).thenReturn(2);
        Mockito.when(energy1.getId()).thenReturn(3);
        Mockito.when(energy2.getId()).thenReturn(4);

        Mockito.when(energy1.getEnergyOutput()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(energy2.getEnergyOutput()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(absrobing1.getHeatAbsorbing()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(absrobing2.getHeatAbsorbing()).thenReturn(Integer.MAX_VALUE);

    }

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException {
        Assert.assertEquals(this.container.getTotalHeatAbsorbing(), 0L);
        Assert.assertEquals(this.container.getTotalEnergyOutput(), 0L);
        this.container.addEnergyModule(energy1);
        this.container.addEnergyModule(energy2);
        this.container.addAbsorbingModule(absrobing1);
        this.container.addAbsorbingModule(absrobing2);
        Field modulesByInput = this.container.getClass().getDeclaredField("modulesByInput");
        modulesByInput.setAccessible(true);
        LinkedList<Module> modulesList = (LinkedList<Module>) modulesByInput.get(this.container);

        Field energyModules = this.container.getClass().getDeclaredField("energyModules");
        energyModules.setAccessible(true);
        Map<Integer, EnergyModule> energyModuleMap = (Map<Integer, EnergyModule>) energyModules.get(this.container);

        Field absorbingModules = this.container.getClass().getDeclaredField("absorbingModules");
        absorbingModules.setAccessible(true);
        Map<Integer, AbsorbingModule> absorbingModuleMap = (Map<Integer, AbsorbingModule>) absorbingModules.get(this.container);

        Assert.assertEquals(modulesList.size(), 4);
        Assert.assertEquals(energyModuleMap.size(), 2);
        Assert.assertEquals(absorbingModuleMap.size(), 2);
        Assert.assertEquals(this.container.getTotalEnergyOutput(), 2L*Integer.MAX_VALUE);
        Assert.assertEquals(this.container.getTotalHeatAbsorbing(), 2L*Integer.MAX_VALUE);


    }

    @Test
    public void test2() throws NoSuchFieldException, IllegalAccessException {
        this.container.addEnergyModule(energy1);
        this.container.addEnergyModule(energy2);
        this.container.addAbsorbingModule(absrobing1);
        this.container.addAbsorbingModule(absrobing2);
        AbsorbingModule absrobing3 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule absrobing4 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(absrobing3.getId()).thenReturn(5);
        Mockito.when(absrobing4.getId()).thenReturn(6);

        Mockito.when(absrobing3.getHeatAbsorbing()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(absrobing4.getHeatAbsorbing()).thenReturn(Integer.MAX_VALUE);

        this.container.addAbsorbingModule(absrobing3);

        Field modulesByInput = this.container.getClass().getDeclaredField("modulesByInput");
        modulesByInput.setAccessible(true);
        LinkedList<Module> modulesList = (LinkedList<Module>) modulesByInput.get(this.container);

        Assert.assertEquals(modulesList.size(), 5);
    }

    @Test
    public void test3() throws NoSuchFieldException, IllegalAccessException {
        this.container.addEnergyModule(energy1);
        this.container.addEnergyModule(energy2);
        this.container.addAbsorbingModule(absrobing1);
        this.container.addAbsorbingModule(absrobing2);
        AbsorbingModule absrobing3 = Mockito.mock(AbsorbingModule.class);
        AbsorbingModule absrobing4 = Mockito.mock(AbsorbingModule.class);
        Mockito.when(absrobing3.getId()).thenReturn(5);
        Mockito.when(absrobing4.getId()).thenReturn(6);

        Mockito.when(absrobing3.getHeatAbsorbing()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(absrobing4.getHeatAbsorbing()).thenReturn(Integer.MAX_VALUE);

        this.container.addAbsorbingModule(absrobing3);
        this.container.addAbsorbingModule(absrobing4);

        Field modulesByInput = this.container.getClass().getDeclaredField("modulesByInput");
        modulesByInput.setAccessible(true);
        LinkedList<Module> modulesList = (LinkedList<Module>) modulesByInput.get(this.container);

        Assert.assertEquals(modulesList.size(), 5);

        Field energyModules = this.container.getClass().getDeclaredField("energyModules");
        energyModules.setAccessible(true);
        Map<Integer, EnergyModule> energyModuleMap = (Map<Integer, EnergyModule>) energyModules.get(this.container);

        Field absorbingModules = this.container.getClass().getDeclaredField("absorbingModules");
        absorbingModules.setAccessible(true);
        Map<Integer, AbsorbingModule> absorbingModuleMap = (Map<Integer, AbsorbingModule>) absorbingModules.get(this.container);

        Assert.assertEquals(energyModuleMap.size(), 1);
        Assert.assertEquals(absorbingModuleMap.size(), 4);

        Assert.assertEquals(this.container.getTotalEnergyOutput(), (long) Integer.MAX_VALUE);
        Assert.assertEquals(this.container.getTotalHeatAbsorbing(), 4L*Integer.MAX_VALUE);

    }

    @Test
    public void test4() throws NoSuchFieldException, IllegalAccessException {
        this.container.addAbsorbingModule(absrobing1);
        this.container.addAbsorbingModule(absrobing2);
        this.container.addEnergyModule(energy1);
        this.container.addEnergyModule(energy2);
        EnergyModule energy3 = Mockito.mock(EnergyModule.class);
        EnergyModule energy4 = Mockito.mock(EnergyModule.class);

        Mockito.when(energy3.getId()).thenReturn(5);
        Mockito.when(energy4.getId()).thenReturn(6);

        Mockito.when(energy3.getEnergyOutput()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(energy4.getEnergyOutput()).thenReturn(Integer.MAX_VALUE);

        this.container.addEnergyModule(energy3);
        this.container.addEnergyModule(energy4);

        Field modulesByInput = this.container.getClass().getDeclaredField("modulesByInput");
        modulesByInput.setAccessible(true);
        LinkedList<Module> modulesList = (LinkedList<Module>) modulesByInput.get(this.container);

        Assert.assertEquals(modulesList.size(), 5);

        Field energyModules = this.container.getClass().getDeclaredField("energyModules");
        energyModules.setAccessible(true);
        Map<Integer, EnergyModule> energyModuleMap = (Map<Integer, EnergyModule>) energyModules.get(this.container);

        Field absorbingModules = this.container.getClass().getDeclaredField("absorbingModules");
        absorbingModules.setAccessible(true);
        Map<Integer, AbsorbingModule> absorbingModuleMap = (Map<Integer, AbsorbingModule>) absorbingModules.get(this.container);

        Assert.assertEquals(energyModuleMap.size(), 4);
        Assert.assertEquals(absorbingModuleMap.size(), 1);

        Assert.assertEquals(this.container.getTotalEnergyOutput(), 4L*Integer.MAX_VALUE);
        Assert.assertEquals(this.container.getTotalHeatAbsorbing(), (long) Integer.MAX_VALUE);

    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullEnergyModuleShouldFail() {
        this.container.addEnergyModule(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullAbsorbingModuleShouldFail() {
        this.container.addAbsorbingModule(null);
    }
}