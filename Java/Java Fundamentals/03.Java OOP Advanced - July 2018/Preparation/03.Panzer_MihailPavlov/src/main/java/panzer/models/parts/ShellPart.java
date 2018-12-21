package panzer.models.parts;

import panzer.contracts.DefenseModifyingPart;

import java.math.BigDecimal;

public class ShellPart extends AbstractPart implements DefenseModifyingPart {
    private int defenseModifier;
    public ShellPart(String model, double weight, BigDecimal price, int additionalParameter) {
        super(model, weight, price);
        this.defenseModifier = additionalParameter;
    }

    @Override
    public int getDefenseModifier() {
        return this.defenseModifier;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Shell Part - %s", this.getModel())).append(System.lineSeparator())
                .append(String.format("+%d Defense", this.getDefenseModifier()));
        return sb.toString();
    }
}
