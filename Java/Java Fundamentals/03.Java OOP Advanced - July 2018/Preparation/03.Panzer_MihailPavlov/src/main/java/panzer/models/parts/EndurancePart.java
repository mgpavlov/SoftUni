package panzer.models.parts;

import panzer.contracts.HitPointsModifyingPart;

import java.math.BigDecimal;

public class EndurancePart extends AbstractPart implements HitPointsModifyingPart {
    private int hitPointsModifier;
    public EndurancePart(String model, double weight, BigDecimal price, int additionalParameter) {
        super(model, weight, price);
        this.hitPointsModifier = additionalParameter;
    }

    @Override
    public int getHitPointsModifier() {
        return this.hitPointsModifier;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Endurance Part - %s", this.getModel())).append(System.lineSeparator())
                .append(String.format("+%d HitPoints", this.getHitPointsModifier()));
        return sb.toString();
    }
}
