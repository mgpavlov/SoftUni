package panzer.models.vehicles;

import java.math.BigDecimal;

public class Vanguard extends AbstractVehicles{
    public Vanguard(String model, double weight, BigDecimal price, int attack, int defense, int hitPoints) {
        super(model, weight*2.00, price, (int) (attack*0.75), (int) (defense*1.5), (int) (hitPoints*1.75));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Vanguard - %s", super.getModel())).append(System.lineSeparator());
        sb.append(super.toString());
        return sb.toString();
    }
}
