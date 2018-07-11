package p01Vehicles;

public class Vehicles {
    private double fuelQuantity;
    private double littersPerKm;

    protected Vehicles(double fuelQuantity, double littersPerKm) {
        this.setFuelQuantity(fuelQuantity);
        this.setLittersPerKm(littersPerKm);
    }


    protected double getFuelQuantity() {
        return this.fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {

        this.fuelQuantity = fuelQuantity;
    }

    protected void setLittersPerKm(double littersPerKm) {
        this.littersPerKm = littersPerKm;
    }

    public boolean drive(double distance) {
        double neededFuel = distance*this.littersPerKm;
        if (neededFuel > this.fuelQuantity) {
            return false;
        }
        this.fuelQuantity -= neededFuel;
        return true;
    }

    public void refuel (double liter){
        this.fuelQuantity += liter;
    }
}
