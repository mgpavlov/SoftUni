package panzer.models.vehicles;

import panzer.contracts.Assembler;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;
import panzer.models.miscellaneous.VehicleAssembler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVehicles implements Vehicle {
    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defense;
    private long hitPoints;

    private Assembler vehicleAssembler;
    private List<Part> parts;

    protected AbstractVehicles(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.vehicleAssembler = new VehicleAssembler();
        this.parts = new ArrayList<>();
    }

    @Override
    public double getTotalWeight() {
        return this.vehicleAssembler.getTotalWeight() + this.weight;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.vehicleAssembler.getTotalPrice().add(this.price);
    }

    @Override
    public long getTotalAttack() {
        return this.vehicleAssembler.getTotalAttackModification() + this.attack;
    }

    @Override
    public long getTotalDefense() {
        return this.vehicleAssembler.getTotalDefenseModification() + this.defense;
    }

    @Override
    public long getTotalHitPoints() {
        return this.vehicleAssembler.getTotalHitPointModification() + this.hitPoints;
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.vehicleAssembler.addArsenalPart(arsenalPart);
        this.parts.add(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.vehicleAssembler.addShellPart(shellPart);
        this.parts.add(shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.vehicleAssembler.addEndurancePart(endurancePart);
        this.parts.add(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() {
        return this.parts;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Total Weight: %.3f", this.getTotalWeight())).append(System.lineSeparator());
        sb.append(String.format("Total Price: %.3f", this.getTotalPrice())).append(System.lineSeparator());
        sb.append(String.format("Attack: %d", this.getTotalAttack())).append(System.lineSeparator());
        sb.append(String.format("Defense: %d", this.getTotalDefense())).append(System.lineSeparator());
        sb.append(String.format("HitPoints: %d", this.getTotalHitPoints())).append(System.lineSeparator());
        sb.append("Parts: ");
        if (this.parts.isEmpty()) {
            sb.append("None").append(System.lineSeparator());
            return sb.toString();
        }
        for (Part part : this.parts) {
            sb.append(part.getModel()).append(", ");
        }

        return sb.toString().substring(0, sb.length() - 2) + System.lineSeparator();
    }
}
