package examPrep22.entities;

import examPrep22.entities.Cars.Car;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Garage {
    private List<Car> parkedCars;

    public Garage() {
        this.parkedCars = new ArrayList<>();
    }

    public boolean hasCar(Car car){
        return this.parkedCars.contains(car);
    }

    public void addInGarage(Car car) {
        this.parkedCars.add(car);
    }

    public void removeFromGarage(Car car) {
        this.parkedCars.remove(car);
    }


    public Collection<Car> getParkedCars() {
        return Collections.unmodifiableList(this.parkedCars);
    }

    public void tune(int tuneIndex, String addOn) {
        for (Car parkedCar : this.parkedCars) {
            parkedCar.tuneCar(tuneIndex, addOn);
        }
    }
}
