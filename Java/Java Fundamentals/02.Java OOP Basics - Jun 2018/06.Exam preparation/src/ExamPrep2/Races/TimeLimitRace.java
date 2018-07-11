package ExamPrep2.Races;

import ExamPrep2.Cars.Car;

import java.util.ArrayList;

public class TimeLimitRace extends Race {
    private int goldTime;

    public TimeLimitRace(int length, String route, int prizePool, int goldTime) {
        super(length, route, prizePool);
        this.goldTime = goldTime;
    }

    @Override
    public void addParticipants(Car participant) {
        if (super.getParticipants().isEmpty()) {
            super.addParticipants(participant);
        }
    }

    @Override
    public String toString() {
        Car car = new ArrayList<>(super.getParticipants()).get(0);
        int timePerformance = car.getTimePerformance() * super.getLength();
        int prize;
        String earnedTime;
        if (timePerformance <= this.goldTime) {
            prize = super.getPrizePool();
            earnedTime ="Gold";
        } else if (timePerformance <= this.goldTime + 15) {
            prize = super.getPrizePool() * 50 / 100;
            earnedTime ="Silver";
        } else {
            prize = super.getPrizePool() * 30 / 100;
            earnedTime ="Bronze";
        }
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(String.format("%s %s - %d s.", car.getBrand(), car.getModel(), timePerformance))
                .append(System.lineSeparator())
                .append(String.format("%s Time, $%d.", earnedTime, prize ));
        return sb.toString().trim();
    }
}
