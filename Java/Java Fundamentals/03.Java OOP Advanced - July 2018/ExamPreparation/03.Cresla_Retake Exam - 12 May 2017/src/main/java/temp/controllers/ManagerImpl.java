/*
package temp.controllers;

import cresla.entities.containers.ModuleContainer;
import cresla.entities.reactors.ReactorAbstract;
import cresla.interfaces.*;
import cresla.interfaces.Module;
import temp.interfaces.ModuleFactory;
import temp.interfaces.ReactorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {
    private OutputWriter writer;
    private ModuleFactory moduleFactory;
    private ReactorFactory reactorFactory;
    private Map<Integer, Reactor> reactors;
    private int id;


    public ManagerImpl(OutputWriter writer, ModuleFactory moduleFactory, ReactorFactory reactorFactory) {
        this.writer = writer;
        this.moduleFactory = moduleFactory;
        this.reactorFactory = reactorFactory;
        this.id = 1;
        this.reactors = new HashMap<>();
    }

    private int getId() {
        return this.id++;
    }


    @Override
    public String reactorCommand(List<String> arguments) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String reactorType = arguments.get(0);
        int additionalParameter = Integer.parseInt(arguments.get(1));
        int moduleCapacity = Integer.parseInt(arguments.get(2));
        int currentId = this.getId();
        ModuleContainer moduleContainer = new ModuleContainer(moduleCapacity);
        Reactor currentReactor = this.reactorFactory.create(reactorType, currentId, moduleContainer, additionalParameter);
        this.reactors.put(currentId, currentReactor);
        return String.format("Created %s Reactor - %d", reactorType, currentId);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        int reactorId = Integer.parseInt(arguments.get(0));
        String type = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));
        int currentId = this.getId();

        Module module = this.moduleFactory.create(type, currentId, additionalParameter);
        if ("CryogenRod".equals(type)) {
            this.reactors.get(reactorId).addEnergyModule((EnergyModule) module);
        } else {
            this.reactors.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
        }

        return String.format("Added %s - %d to Reactor - %d", type, currentId, reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int reportId = Integer.parseInt(arguments.get(0));
        return this.reactors.get(reportId).toString();
    }

    @Override
    public String exitCommand(List<String> arguments) throws NoSuchFieldException, IllegalAccessException {
        int cryoReactorsCount = 0;
        int heatReactorsCount = 0;
        int energyModulesCount = 0;
        int absorbingModulesCount = 0;
        int totalEnergyOutput = 0;
        int totalHeatAbsorbing = 0;
        for (Reactor reactor : reactors.values()) {
            if (reactor.getClass().getSimpleName().equals("CryoReactor")) {
                cryoReactorsCount++;
            }
            if (reactor.getClass().getSimpleName().equals("HeatReactor")) {
                heatReactorsCount++;
            }
            totalEnergyOutput += reactor.getTotalEnergyOutput();
            totalHeatAbsorbing += reactor.getTotalHeatAbsorbing();


            Field container = ReactorAbstract.class.getDeclaredField("moduleContainer");
            container.setAccessible(true);
            ModuleContainer moduleContainer = (ModuleContainer) container.get(reactor);

            for (Module module : moduleContainer.getAllModules()) {
                if (module.getClass().getSimpleName().equals("CryogenRod")) {
                    energyModulesCount++;
                } else {
                    absorbingModulesCount++;
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cryo Reactors: ").append(cryoReactorsCount).append(System.lineSeparator());
        sb.append("Heat Reactors: ").append(heatReactorsCount).append(System.lineSeparator());
        sb.append("Energy Modules: ").append(energyModulesCount).append(System.lineSeparator());
        sb.append("Absorbing Modules: ").append(absorbingModulesCount).append(System.lineSeparator());
        sb.append("Total Energy Output: ").append(totalEnergyOutput).append(System.lineSeparator());
        sb.append("Total Heat Absorbing: ").append(totalHeatAbsorbing);

        return sb.toString();
    }
}
*/
