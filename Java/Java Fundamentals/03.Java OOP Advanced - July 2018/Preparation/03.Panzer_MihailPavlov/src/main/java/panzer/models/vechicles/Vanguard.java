package panzer.models.vechicles;

import panzer.contracts.Assembler;

import java.math.BigDecimal;

public class Vanguard extends AbstractVehicle{
    public Vanguard(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints, Assembler assembler) {
        super(model, weight*2, price, (int) (attack*0.75), (int) (defense*1.50), (int) (hitPoints*1.75), assembler);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %s", this.getClass().getSimpleName(), this.getModel()))
                .append(System.lineSeparator())
                .append(super.toString());
        return sb.toString();
    }
}
