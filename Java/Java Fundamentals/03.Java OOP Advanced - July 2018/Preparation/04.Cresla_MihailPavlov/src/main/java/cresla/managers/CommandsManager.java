package cresla.managers;

import cresla.entities.containers.ModuleContainer;
import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.Identifiable;
import cresla.interfaces.Manager;
import cresla.interfaces.Reactor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandsManager implements Manager {
    private Map<Integer, Reactor> reactors = new HashMap<>();
    private Map<Integer, Identifiable> identifiables = new HashMap<>();

    private int cryoReactorsCount = 0;
    private int heatReactorsCount = 0;
    private int energyModulesCount = 0;
    private int absorbingModulesCount = 0;

    @Override
    public String reactorCommand(List<String> arguments) {
        String reactorType = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));
        int moduleCapacity = Integer.parseInt(arguments.get(3));

        Reactor reactor;

        switch (reactorType) {
            case "Cryo":
                reactor = new CryoReactor(new ModuleContainer(moduleCapacity), additionalParameter);
                this.cryoReactorsCount++;
                break;
            case "Heat":
                reactor = new HeatReactor(new ModuleContainer(moduleCapacity), additionalParameter);
                this.heatReactorsCount++;
                break;
            default:
                return null;
        }

        this.identifiables.put(reactor.getId(), reactor);
        this.reactors.put(reactor.getId(), reactor);

        return String.format("Created %s Reactor - %d", reactorType, reactor.getId());
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        int reactorId = Integer.parseInt(arguments.get(1));
        String type = arguments.get(2);
        int additionalParameter = Integer.parseInt(arguments.get(3));

        int moduleId;
        Identifiable module;
        switch (type) {
            case "CryogenRod":
                CryogenRod cryogenRod = new CryogenRod(additionalParameter);
                moduleId = cryogenRod.getId();
                module = cryogenRod;
                this.energyModulesCount++;
                this.reactors.get(reactorId).addEnergyModule(cryogenRod);
                break;
            case "HeatProcessor":
                HeatProcessor heatProcessor = new HeatProcessor(additionalParameter);
                moduleId = heatProcessor.getId();
                module = heatProcessor;
                this.absorbingModulesCount++;
                this.reactors.get(reactorId).addAbsorbingModule(heatProcessor);
                break;
            case "CooldownSystem":
                CooldownSystem cooldownSystem = new CooldownSystem(additionalParameter);
                moduleId = cooldownSystem.getId();
                module = cooldownSystem;
                this.absorbingModulesCount++;
                this.reactors.get(reactorId).addAbsorbingModule(cooldownSystem);
                break;
            default:
                return null;
        }

        this.identifiables.put(moduleId, module);

        return String.format("Added %s - %d to Reactor - %d", type, moduleId, reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int id = Integer.parseInt(arguments.get(1));

        if (this.identifiables.containsKey(id)) {
            return this.identifiables.get(id).toString();
        }

        return null;
    }

    @Override
    public String exitCommand(List<String> arguments) {
        long totalEnergyOutput = reactors.values().stream().mapToLong(Reactor::getTotalEnergyOutput).sum();
        long totalHeatAbsorbing = reactors.values().stream().mapToLong(Reactor::getTotalHeatAbsorbing).sum();

        StringBuilder sb = new StringBuilder();

        sb.append("Cryo Reactors: ").append(this.cryoReactorsCount).append(System.lineSeparator());
        sb.append("Heat Reactors: ").append(this.heatReactorsCount).append(System.lineSeparator());
        sb.append("Energy Modules: ").append(this.energyModulesCount).append(System.lineSeparator());
        sb.append("Absorbing Modules: ").append(this.absorbingModulesCount).append(System.lineSeparator());
        sb.append("Total Energy Output: ").append(totalEnergyOutput).append(System.lineSeparator());
        sb.append("Total Heat Absorbing: ").append(totalHeatAbsorbing);

        return sb.toString();
    }
}
