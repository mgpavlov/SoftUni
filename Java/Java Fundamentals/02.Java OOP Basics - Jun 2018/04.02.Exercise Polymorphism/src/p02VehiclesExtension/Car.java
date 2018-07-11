package p02VehiclesExtension;

public class Car extends Vehicles {
    private static final double SUMMER_CONSUMPTION_ADDITION = 0.90;

    protected Car(double fuelQuantity, double littersPerKm, double tankCapacity) {
        super(fuelQuantity, littersPerKm, tankCapacity);
    }


    @Override
    protected void setLittersPerKm(double littersPerKm) {
        super.setLittersPerKm(littersPerKm + SUMMER_CONSUMPTION_ADDITION);
    }
}
