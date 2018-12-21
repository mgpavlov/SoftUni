package panzer.manager;

import panzer.contracts.Manager;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.core.PanzerBattleOperator;
import panzer.models.parts.ArsenalPart;
import panzer.models.parts.EndurancePart;
import panzer.models.parts.ShellPart;
import panzer.models.vehicles.Revenger;
import panzer.models.vehicles.Vanguard;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProgramManager implements Manager {
    private Map<String, Vehicle> vehicles;
    private Map<String, Part> parts;
    private Map<String, Vehicle> defeatedVehicles;
    private PanzerBattleOperator panzerBattleOperator;

    public ProgramManager() {
        this.vehicles = new LinkedHashMap<>();
        this.parts = new LinkedHashMap<>();
        this.defeatedVehicles = new LinkedHashMap<>();
        this.panzerBattleOperator = new PanzerBattleOperator();
    }

    @Override
    public String addVehicle(List<String> arguments) {
        Vehicle vehicle = null;
        String vehicleType = arguments.get(1);
        String vehicleModel = arguments.get(2);
        double weight = Double.parseDouble(arguments.get(3));
        BigDecimal price = new BigDecimal(arguments.get(4));
        int attack = Integer.parseInt(arguments.get(5));
        int defense = Integer.parseInt(arguments.get(6));
        int hitPoints = Integer.parseInt(arguments.get(7));
        switch (vehicleType) {
            case "Vanguard":
                vehicle = new Vanguard(vehicleModel, weight, price, attack, defense, hitPoints);
                break;
            case "Revenger":
                vehicle = new Revenger(vehicleModel, weight, price, attack, defense, hitPoints);
                break;
        }
        vehicles.put(vehicleModel, vehicle);
        return String.format("Created %s Vehicle - %s", vehicleType, vehicleModel);
    }

    @Override
    public String addPart(List<String> arguments) {
        Part part = null;
        String vehicleModel = arguments.get(1);
        String partType = arguments.get(2);
        String partModel = arguments.get(3);
        double weight = Double.parseDouble(arguments.get(4));
        BigDecimal price = new BigDecimal(arguments.get(5));
        int additionalParameter = Integer.parseInt(arguments.get(6));
        switch (partType) {
            case "Arsenal":
                part = new ArsenalPart(partModel, weight, price, additionalParameter);
                if (vehicles.containsKey(vehicleModel)) {
                    vehicles.get(vehicleModel).addArsenalPart(part);
                }
                break;
            case "Shell":
                part = new ShellPart(partModel, weight, price, additionalParameter);
                if (vehicles.containsKey(vehicleModel)) {
                    vehicles.get(vehicleModel).addShellPart(part);
                }
                break;
            case "Endurance":
                part = new EndurancePart(partModel, weight, price, additionalParameter);
                if (vehicles.containsKey(vehicleModel)) {
                    vehicles.get(vehicleModel).addEndurancePart(part);
                }
                break;
        }
        parts.put(partModel, part);

        return String.format("Added %s - %s to Vehicle - %s", partType, partModel, vehicleModel);
    }

    @Override
    public String inspect(List<String> arguments) {
        String model = arguments.get(1);
        if (vehicles.containsKey(model)) {
            return vehicles.get(model).toString();
        } else if (parts.containsKey(model)) {
            return parts.get(model).toString();
        }
        return "";
    }

    @Override
    public String battle(List<String> arguments) {
        String modelVehicleAttacker = arguments.get(1);
        String modelVehicleTarget = arguments.get(2);

        Vehicle attacker = vehicles.get(modelVehicleAttacker);
        Vehicle target = vehicles.get(modelVehicleTarget);

        String model = this.panzerBattleOperator.battle(attacker, target);
        if (model.equals(modelVehicleAttacker)){
            this.defeatedVehicles.put(modelVehicleTarget, target);
            this.vehicles.remove(modelVehicleTarget);
            return String.format("%s versus %s -> %s Wins! Flawless Victory!", modelVehicleAttacker, modelVehicleTarget, modelVehicleAttacker);
        }
        this.defeatedVehicles.put(modelVehicleAttacker, attacker);
        this.vehicles.remove(modelVehicleAttacker);
        return String.format("%s versus %s -> %s Wins! Flawless Victory!", modelVehicleAttacker, modelVehicleTarget, modelVehicleTarget);
    }

    @Override
    public String terminate(List<String> arguments) {
        StringBuilder sb = new StringBuilder();
        sb.append("Remaining Vehicles: ");
        if (this.vehicles.isEmpty()){
            sb.append("None");
        }else {
            for (Map.Entry<String, Vehicle> remainVehicle : this.vehicles.entrySet()) {
                sb.append(remainVehicle.getValue().getModel()).append(", ");
            }
            sb = new StringBuilder(sb.toString().substring(0, sb.toString().length()-2));
        }
        sb.append(System.lineSeparator());

        sb.append("Defeated Vehicles: ");
        if (this.defeatedVehicles.isEmpty()){
            sb.append("None");
        }else {
            for (Map.Entry<String, Vehicle> defeatedVehicle : this.defeatedVehicles.entrySet()) {
                sb.append(defeatedVehicle.getValue().getModel()).append(", ");
            }
            sb = new StringBuilder(sb.toString().substring(0, sb.toString().length()-2));
        }
        sb.append(System.lineSeparator());

        sb.append(String.format("Currently Used Parts: %d", this.vehicles.entrySet().stream().mapToInt(v-> {
            List<Part> tempList = new ArrayList<Part>((Collection<? extends Part>) v.getValue().getParts());
            return tempList.size();
        }).sum()));

        return sb.toString();
    }
}
