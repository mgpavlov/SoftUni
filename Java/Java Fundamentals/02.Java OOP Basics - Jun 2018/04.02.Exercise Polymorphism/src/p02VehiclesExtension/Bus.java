package p02VehiclesExtension;

public class Bus extends Vehicles {
    private final double SUMMER_CONSUMPTION_ADDED = 1.40;
    protected boolean isEmpty = false;
    protected Bus(double fuelQuantity, double littersPerKm, double tankCapacity) {
        super(fuelQuantity, littersPerKm, tankCapacity);
    }

    @Override
    protected void setLittersPerKm(double littersPerKm) {
        if (this.isEmpty){
            super.setLittersPerKm(littersPerKm);
        }else {
            super.setLittersPerKm(littersPerKm + SUMMER_CONSUMPTION_ADDED);
        }
    }

}
