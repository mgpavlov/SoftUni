package panzer.models.parts;

import panzer.contracts.AttackModifyingPart;

import java.math.BigDecimal;

public class ArsenalPart extends AbstractPart implements AttackModifyingPart {
    private int attackModifier;
    public ArsenalPart(String model, double weight, BigDecimal price, int additionalParameter) {
        super(model, weight, price);
        this.attackModifier = additionalParameter;
    }

    @Override
    public int getAttackModifier() {
        return this.attackModifier;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Arsenal Part - %s", this.getModel())).append(System.lineSeparator())
                .append(String.format("+%d Attack", this.getAttackModifier()));
        return sb.toString();
    }
}
