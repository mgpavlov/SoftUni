package p05SpeedRacing;

import java.text.DecimalFormat;

public class Car {
    private String model;
    private Double fuelAmount;
    private Double fuelCostPerKilometer;
    private Double distanceTraveled;

    public Car(String model, Double fuelAmount, Double fuelCostPerKilometer) {
        this.setModel(model);
        this.setFuelAmount(fuelAmount);
        this.setFuelCostPerKilometer(fuelCostPerKilometer);
        this.setDistanceTraveled(distanceTraveled);
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setFuelAmount(Double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    private void setFuelCostPerKilometer(Double fuelCostPerKilometer) {
        this.fuelCostPerKilometer = fuelCostPerKilometer;
    }

    private void setDistanceTraveled(Double distanceTraveled) {
        this.distanceTraveled = 0.0;
    }
    public String getModel() {
        return model;
    }
    public boolean drive (double kilometers){
        double fuelNeeded = kilometers * fuelCostPerKilometer;

        if (this.fuelAmount >= fuelNeeded){
            this.fuelAmount -= fuelNeeded;
            this.distanceTraveled += kilometers;
            return true;
        }
        return false;
    }
    public String toString () {
        DecimalFormat df = new DecimalFormat("0.00");
        DecimalFormat df2 = new DecimalFormat("#.##");
        return this.model + " " + df.format(this.fuelAmount) + " " + df2.format(this.distanceTraveled);
    }


}
