package ExamPrep2;

import ExamPrep2.Cars.Car;
import ExamPrep2.Cars.PerformanceCar;
import ExamPrep2.Cars.ShowCar;
import ExamPrep2.Races.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarManager {
    private Map<Integer, Car> carRegisters;
    private Map<Integer, Race> raceRegisters;
    private Garage garage;

    public CarManager() {
        this.carRegisters = new LinkedHashMap<>();
        this.raceRegisters = new LinkedHashMap<>();
        this.garage = new Garage();

    }

    public void register(int id, String type, String brand,
                         String model, int yearOfProduction, int horsepower,
                         int acceleration, int suspension, int durability) {
        Car car = null;
        switch (type) {
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
        if (car != null) {
            this.carRegisters.put(id, car);
        }
    }

    public String check(int id) {
        return this.carRegisters.get(id).toString();
    }

    public void open(int id, String type, int length, String route, int prizePool, int param) {
        Race race = null;
        switch (type) {
            case "Casual":
                race = new CasualRace(length, route, prizePool);
                break;
            case "Drag":
                race = new DragRace(length, route, prizePool);
                break;
            case "Drift":
                race = new DriftRace(length, route, prizePool);
                break;
            case "TimeLimit":
                race = new TimeLimitRace(length, route, prizePool, param);
                break;
            case "Circuit":
                race = new CircuitRace(length, route, prizePool, param);
                break;
        }
        if (race != null) {
            raceRegisters.put(id, race);
        }
    }

    public void participate(int carId, int raceId) {
        if (this.garage.hasCar(carRegisters.get(carId))) {
            return;
        }
        raceRegisters.get(raceId).addParticipants(carRegisters.get(carId));
    }

    public String start(int raceId) {
        Race theRace = raceRegisters.get(raceId);
        if (theRace.getParticipants().isEmpty()) {
            return "Cannot start the race with zero participants.";
        }
        String result = theRace.toString();
        raceRegisters.remove(raceId);
        return result;
    }

    public void park(int id) {
        Car car = this.carRegisters.get(id);
        if (this.raceRegisters.values().stream().anyMatch(race -> race.hasCar(car))) {
            return;
        }
        this.garage.addInGarage(car);
    }

    public void unpark(int id) {
        Car car = carRegisters.get(id);
        this.garage.removeFromGarage(car);

    }

    public void tune(int tuneIndex, String addOn) {
        for (Car car : this.garage.getParkedCars()) {
            car.tuneCar(tuneIndex, addOn);
        }
    }
}
