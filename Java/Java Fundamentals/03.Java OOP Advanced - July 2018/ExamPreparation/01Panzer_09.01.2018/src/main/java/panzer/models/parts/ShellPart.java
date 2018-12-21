package panzer.models.parts;

import panzer.contracts.DefenseModifyingPart;

import java.math.BigDecimal;

public class ShellPart extends AbstractPart implements DefenseModifyingPart {
    private int defenseModifier;
    public ShellPart(String model, double weight, BigDecimal price, int defenseModifier) {
        super(model, weight, price);
        this.defenseModifier = defenseModifier;
    }

    @Override
    public int getDefenseModifier() {
        return this.defenseModifier;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Shell Part - %s", super.getModel())).append(System.lineSeparator());
        sb.append(String.format("+%d defenseModifier", this.getDefenseModifier())).append(System.lineSeparator());

        return sb.toString();
    }
}
