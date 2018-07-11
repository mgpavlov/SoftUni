package p02VehiclesExtension;

public class Vehicles {
    private double fuelQuantity;
    private double littersPerKm;
    private double tankCapacity;

    protected Vehicles(double fuelQuantity, double littersPerKm, double tankCapacity) {
        this.setFuelQuantity(fuelQuantity);
        this.setLittersPerKm(littersPerKm);
        this.setTankCapacity(tankCapacity);
    }


    protected double getFuelQuantity() {
        return this.fuelQuantity;
    }

    protected void setFuelQuantity(double fuelQuantity) {
        if (fuelQuantity < 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
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
        if (liter <= 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }
        if (this.fuelQuantity + liter > this.tankCapacity){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }else {
            this.fuelQuantity += liter;
        }
    }

    protected void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public double getLittersPerKm() {
        return this.littersPerKm;
    }
}
