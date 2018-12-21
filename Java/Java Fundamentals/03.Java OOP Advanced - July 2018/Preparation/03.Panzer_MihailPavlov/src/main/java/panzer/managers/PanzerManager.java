package panzer.managers;

import panzer.contracts.*;
import panzer.core.PanzerBattleOperator;
import panzer.models.miscellaneous.VehicleAssembler;
import panzer.models.parts.ArsenalPart;
import panzer.models.parts.EndurancePart;
import panzer.models.parts.ShellPart;
import panzer.models.vechicles.Revenger;
import panzer.models.vechicles.Vanguard;

import java.math.BigDecimal;
import java.util.*;

public class PanzerManager implements Manager {
    private Map<String, Vehicle> vehicles;
    private Map<String, Vehicle> defeatedVehicles;
    private Map<String, Part> parts;


    public PanzerManager() {
        this.vehicles = new LinkedHashMap<>();
        this.parts = new LinkedHashMap<>();
        this.defeatedVehicles = new LinkedHashMap<>();
    }

    @Override
    public String addVehicle(List<String> arguments) {
        String type = arguments.get(0);
        String model = arguments.get(1);
        double weight = Double.parseDouble(arguments.get(2));
        BigDecimal price = new BigDecimal(arguments.get(3));
        int attack = Integer.parseInt(arguments.get(4));
        int defense = Integer.parseInt(arguments.get(5));
        int hitPoints = Integer.parseInt(arguments.get(6));
        Assembler assembler = new VehicleAssembler();
        Vehicle vehicle = null;
        switch (type){
            case "Vanguard":
                vehicle = new Vanguard(model, weight, price, attack, defense, hitPoints, assembler);
                break;
            case "Revenger":
                vehicle = new Revenger(model, weight, price, attack, defense, hitPoints, assembler);
                break;
        }
        vehicles.put(model, vehicle);

        return String.format("Created %s Vehicle - %s", type, model);
    }

    @Override
    public String addPart(List<String> arguments) {
        String vehicleModel = arguments.get(0);
        String type = arguments.get(1);
        String model = arguments.get(2);
        double weight = Double.parseDouble(arguments.get(3));
        BigDecimal price = new BigDecimal(arguments.get(4));
        int additionalParameter = Integer.parseInt(arguments.get(5));

        Part part = null;
        if (!vehicles.containsKey(vehicleModel)){
            return "";
        }
        switch (type){
            case "Arsenal":
                part = new ArsenalPart(model, weight, price, additionalParameter);
                vehicles.get(vehicleModel).addArsenalPart(part);
                break;
            case "Shell":
                part = new ShellPart(model, weight, price, additionalParameter);
                vehicles.get(vehicleModel).addShellPart(part);
                break;
            case "Endurance":
                part = new EndurancePart(model, weight, price, additionalParameter);
                vehicles.get(vehicleModel).addEndurancePart(part);
                break;
        }

        parts.put(model, part);
        return String.format("Added %s - %s to Vehicle - %s", type, model, vehicleModel);
    }

    @Override
    public String inspect(List<String> arguments) {
        if (vehicles.containsKey(arguments.get(0))){
            return vehicles.get(arguments.get(0)).toString();
        }else{
            return parts.get(arguments.get(0)).toString();
        }
    }

    @Override
    public String battle(List<String> arguments) {
        Vehicle attacker = vehicles.get(arguments.get(0));
        Vehicle target = vehicles.get(arguments.get(1));
        BattleOperator battelOperator = new PanzerBattleOperator();
        String winnerModel = battelOperator.battle(attacker, target);
        if (winnerModel.equals(attacker.getModel())){
            vehicles.remove(target.getModel());
            defeatedVehicles.put(target.getModel(), target);
        }else {
            vehicles.remove(attacker.getModel());
            defeatedVehicles.put(attacker.getModel(), attacker);
        }
        return String.format("%s versus %s -> %s Wins! Flawless Victory!", attacker.getModel(), target.getModel(), winnerModel);
    }

    @Override
    public String terminate(List<String> arguments) {
        StringBuilder sb = new StringBuilder();
        String remaining = vehicles.size() == 0? "None": String.join(", ", vehicles.keySet());
        String defeated = defeatedVehicles.size()==0? "None": String.join(", ", defeatedVehicles.keySet());
        long partsCount = 0;
        for (Vehicle vehicle : this.vehicles.values()) {
            partsCount += new ArrayList<Part>((Collection<? extends Part>) vehicle.getParts()).size();
        }
        sb
                .append(String.format("Remaining Vehicles: %s", remaining)).append(System.lineSeparator())
                .append(String.format("Defeated Vehicles: %s", defeated)).append(System.lineSeparator())
                .append(String.format("Currently Used Parts: %d", partsCount));

        return sb.toString();
    }
}
