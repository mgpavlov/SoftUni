package cresla.entities.reactors;

import cresla.entities.AbstractIdentifiable;
import cresla.interfaces.*;
import cresla.interfaces.Module;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.rmi.MarshalledObject;
import java.util.List;

public abstract class ReactorAbstract extends AbstractIdentifiable implements Reactor {
    /*private int id;*/
    private Container moduleContainer;

    protected ReactorAbstract(/*int id, */Container moduleContainer) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        /*this.id = id;*/
        super();
       this.moduleContainer = moduleContainer;
    }

    @Override
    public long getTotalEnergyOutput() {
        return this.moduleContainer.getTotalEnergyOutput();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.moduleContainer.getTotalHeatAbsorbing();
    }

    @Override
    public int getModuleCount() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Field modulesByInput = this.moduleContainer.getClass().getDeclaredField("modulesByInput");
        modulesByInput.setAccessible(true);
        List<Module> modulesByInputList = (List<Module>) modulesByInput.get(this.moduleContainer);

        return modulesByInputList.size();
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.moduleContainer.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.moduleContainer.addAbsorbingModule(absorbingModule);
    }

    /*@Override
    public int getId() {
        return this.id;
    }*/

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %d", this.getClass().getSimpleName(), this.getId())).append(System.lineSeparator());
        sb.append(String.format("Energy Output: %d", this.getTotalEnergyOutput())).append(System.lineSeparator());
        sb.append(String.format("Heat Absorbing: %d", this.getTotalHeatAbsorbing())).append(System.lineSeparator());
        try {
            sb.append(String.format("Modules: %d", this.getModuleCount()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
