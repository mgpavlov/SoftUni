package p01Vehicles;

public class Car extends Vehicles{
    private static final double SUMMER_CONSUMPTION_ADDITION = 0.90;
    public Car(double fuelQuantity, double littersPerKm) {
        super(fuelQuantity, littersPerKm);
    }

    @Override
    protected void setLittersPerKm(double littersPerKm) {
        super.setLittersPerKm(littersPerKm + SUMMER_CONSUMPTION_ADDITION);
    }
}
