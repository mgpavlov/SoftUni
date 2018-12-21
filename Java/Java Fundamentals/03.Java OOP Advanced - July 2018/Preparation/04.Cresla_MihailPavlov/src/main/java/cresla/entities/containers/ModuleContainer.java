package cresla.entities.containers;

import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.Container;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Module;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class ModuleContainer implements Container {

    private int moduleCapacity;
    private LinkedList<Module> modulesByInput;
    private Map<Integer, EnergyModule> energyModules;
    private Map<Integer, AbsorbingModule> absorbingModules;

    public ModuleContainer(int moduleCapacity) {
        this.moduleCapacity = moduleCapacity;
        this.modulesByInput = new LinkedList<>();
        this.energyModules = new LinkedHashMap<>();
        this.absorbingModules = new LinkedHashMap<>();
    }

    public void addEnergyModule(EnergyModule energyModule) {
        prepareToAddModule(energyModule);
        this.energyModules.put(energyModule.getId(), energyModule);
    }


    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        prepareToAddModule(absorbingModule);
        this.absorbingModules.put(absorbingModule.getId(), absorbingModule);
    }

    @Override
    public long getTotalEnergyOutput() {
        return this.energyModules.values().stream()
                .mapToLong(EnergyModule::getEnergyOutput)
                .sum();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.absorbingModules.values().stream()
                .mapToLong(AbsorbingModule::getHeatAbsorbing)
                .sum();
    }

    private void removeOldestModule() {
        int removeId = this.modulesByInput.removeFirst().getId();

        if (this.energyModules.containsKey(removeId)) {
            this.energyModules.remove(removeId);
        } else if (this.absorbingModules.containsKey(removeId)) {
            this.absorbingModules.remove(removeId);
        }
    }

    private void prepareToAddModule(Module module) {
        if (module == null) {
            throw new IllegalArgumentException();
        }

        if (this.modulesByInput.size() == this.moduleCapacity) {
            this.removeOldestModule();
        }

        this.modulesByInput.addLast(module);
    }
}