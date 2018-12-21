package panzer.models.vechicles;

import panzer.contracts.Assembler;

import java.math.BigDecimal;

public class Revenger extends AbstractVehicle{
    public Revenger(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints, Assembler assembler) {
        super(model, weight, price.multiply(BigDecimal.valueOf(1.50)), (int) (attack*2.50), (int) (defense*0.50), (int) (hitPoints*0.50), assembler);
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
