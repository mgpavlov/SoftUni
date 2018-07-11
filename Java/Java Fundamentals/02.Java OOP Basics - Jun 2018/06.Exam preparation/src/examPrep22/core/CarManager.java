package examPrep22.core;

import examPrep22.entities.Cars.Car;
import examPrep22.entities.Cars.PerformanceCar;
import examPrep22.entities.Cars.ShowCar;
import examPrep22.entities.*;
import examPrep22.entities.Races.CasualRace;
import examPrep22.entities.Races.DragRace;
import examPrep22.entities.Races.DriftRace;
import examPrep22.entities.Races.Race;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarManager {
    private Map<Integer, Car> cars;
    private Map<Integer, Race> races;
    private Garage garage;

    public CarManager (){
        this.cars = new LinkedHashMap<>();
        this.races = new LinkedHashMap<>();
        this.garage = new Garage();

    }
    public 	void register(int id, String type, String brand,
                            String model, int yearOfProduction, int horsepower,
                            int acceleration, int suspension, int durability){
        Car car = null;
        switch (type){
            case "Performance":
                car = new PerformanceCar(brand, model,
                        yearOfProduction, horsepower,
                        acceleration, suspension, durability);
                break;
            case "Show":
                car = new ShowCar(brand, model,
                        yearOfProduction, horsepower,
                        acceleration, suspension, durability);
                break;
        }
        if (car !=null){
            this.cars.put(id, car);
        }
    }

    public String check(int id){
        return this.cars.get(id).toString();
    }

    public void open(int id, String type, int length, String route, int prizePool){
        Race race = null;
        switch (type){
            case "Casual":
                race = new CasualRace(length, route, prizePool);
                break;
            case "Drag":
                race = new DragRace(length, route, prizePool);
                break;
            case "Drift":
                race = new DriftRace(length, route, prizePool);
                break;
        }
        if (race != null){
            races.put(id, race);
        }
    }

    public void participate(int carId, int raceId){
        if (this.garage.hasCar(cars.get(carId))){
            return;
        }
        races.get(raceId).addParticipants(cars.get(carId));
    }

    public String start(int raceId){
        Race theRace = races.get(raceId);
        if(theRace.getParticipants().isEmpty()){
            return"Cannot start the race with zero participants.";
        }
        String result = theRace.toString();
        races.remove(raceId);
        return  result;
    }

    public void park(int id){
        Car car = this.cars.get(id);
        if (this.races.values().stream().anyMatch(race -> race.hasCar(car))){
            return;
        }
        this.garage.addInGarage(car);
    }
    public void unpark(int id){
        Car car = cars.get(id);
            this.garage.removeFromGarage(car);

    }

    public void tune(int tuneIndex, String addOn){
        this.garage.tune(tuneIndex, addOn);
        /*for (Car car : this.garage.getParkedCars()) {
            car.tuneCar(tuneIndex, addOn);
        }*/
    }
}
