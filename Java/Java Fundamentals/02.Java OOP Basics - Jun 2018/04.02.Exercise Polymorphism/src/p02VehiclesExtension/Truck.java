package p02VehiclesExtension;

public class Truck extends Vehicles {
    private static final double SUMMER_CONSUMPTION_ADDITION = 1.60;

    protected Truck(double fuelQuantity, double littersPerKm, double tankCapacity) {
        super(fuelQuantity, littersPerKm, tankCapacity);
    }


    @Override
    protected void setLittersPerKm(double littersPerKm) {
        super.setLittersPerKm(littersPerKm + SUMMER_CONSUMPTION_ADDITION);
    }

    @Override
    public void refuel(double liter) {
        super.refuel(liter*0.95);
    }
}
