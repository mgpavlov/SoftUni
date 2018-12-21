package panzer.models.vechicles;

import panzer.contracts.Assembler;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractVehicle implements Vehicle {
    private String model;
    private double weight;
    private BigDecimal price;
    private int attack;
    private int defense;
    private int hitPoints;
    private Assembler assembler;
    private ArrayList<Part> allParts;//todo

    protected AbstractVehicle(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints, Assembler assembler) {
        this.model = model;
        this.weight = weight;
        this.price = price;
        this.attack = attack;
        this.defense = defense;
        this.hitPoints = hitPoints;
        this.assembler = assembler;
        this.allParts = new ArrayList<>();
    }

    @Override
    public double getTotalWeight() {
        return this.weight + this.assembler.getTotalWeight();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return this.price.add(this.assembler.getTotalPrice());
    }

    @Override
    public long getTotalAttack() {
        return this.attack + this.assembler.getTotalAttackModification();
    }

    @Override
    public long getTotalDefense() {
        return this.defense + this.assembler.getTotalDefenseModification();
    }

    @Override
    public long getTotalHitPoints() {
        return this.hitPoints+this.assembler.getTotalHitPointModification();
    }

    @Override
    public void addArsenalPart(Part arsenalPart) {
        this.assembler.addArsenalPart(arsenalPart);
        this.allParts.add(arsenalPart);
    }

    @Override
    public void addShellPart(Part shellPart) {
        this.assembler.addShellPart(shellPart);
        this.allParts.add(shellPart);
    }

    @Override
    public void addEndurancePart(Part endurancePart) {
        this.assembler.addEndurancePart(endurancePart);
        this.allParts.add(endurancePart);
    }

    @Override
    public Iterable<Part> getParts() {
        return this.allParts;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Total Weight: %.3f", this.getTotalWeight())).append(System.lineSeparator())
                .append(String.format("Total Price: %.3f", this.getTotalPrice())).append(System.lineSeparator())
                .append(String.format("Attack: %d", this.getTotalAttack())).append(System.lineSeparator())
                .append(String.format("Defense: %d", this.getTotalDefense())).append(System.lineSeparator())
                .append(String.format("HitPoints: %d", this.getTotalHitPoints())).append(System.lineSeparator())
                .append("Parts: ");

        List<String> allPartsPrint = new ArrayList<>();
        for (Part part : this.getParts()) {
            allPartsPrint.add(part.getModel());
        }
        String printParts = allPartsPrint.size() == 0? "None": String.join(", ", allPartsPrint);
        sb.append(printParts);
        return sb.toString();
    }
}
