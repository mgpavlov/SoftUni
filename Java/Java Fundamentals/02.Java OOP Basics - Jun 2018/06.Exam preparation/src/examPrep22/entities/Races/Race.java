package examPrep22.entities.Races;

import examPrep22.entities.Cars.Car;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Race {
    private int length;
    private String route;
    private int prizePool;
    private List<Car> participants;

    protected Race(int length, String route, int prizePool) {
        this.length = length;
        this.route = route;
        this.prizePool = prizePool;
        this.participants = new ArrayList<>();
    }

    public int getLength() {
        return this.length;
    }

    public String getRoute() {
        return this.route;
    }

    public int getPrizePool() {
        return this.prizePool;
    }

    public Collection<Car> getParticipants() {
        return Collections.unmodifiableList(this.participants);
    }

    public void addParticipants(Car participant) {
        this.participants.add(participant);
    }


    public boolean hasCar(Car car){
        return this.participants.contains(car);
    };

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %d", this.route, this.length))
                .append(System.lineSeparator());
        return sb.toString();
    }
}
