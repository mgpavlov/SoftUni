package panzer.models.vehicles;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;

public class Revenger extends AbstractVehicles{
    public Revenger(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight, price.multiply(BigDecimal.valueOf(1.50)), (int) (attack*2.50), (int) (defense*0.50), (int) (hitPoints*0.50));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Revenger - %s", super.getModel())).append(System.lineSeparator());
        sb.append(super.toString());
        return sb.toString();
    }
}
