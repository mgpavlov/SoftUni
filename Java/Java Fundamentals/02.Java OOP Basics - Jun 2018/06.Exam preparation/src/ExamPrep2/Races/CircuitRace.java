package ExamPrep2.Races;

import ExamPrep2.Cars.Car;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CircuitRace extends Race {
    private int lap;
    public CircuitRace(int length, String route, int prizePool, int lap) {
        super(length, route, prizePool);
        this.lap = lap;
    }

    @Override
    public void addParticipants(Car participant) {
        int decreaseParam = super.getLength()*super.getLength();
        for (int i = 0; i < lap; i++) {
            participant.decreasesDurability(decreaseParam);
        }
        super.addParticipants(participant);
    }

    @Override
    public String toString() {
        int position = 1;
        Map<Integer, Integer> positionPrize = new TreeMap<>();
        positionPrize.put(1, (super.getPrizePool() * 40) / 100);
        positionPrize.put(2, (super.getPrizePool() * 30) / 100);
        positionPrize.put(3, (super.getPrizePool() * 20) / 100);
        positionPrize.put(4, (super.getPrizePool() * 10) / 100);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s - %d", super.getRoute(), super.getLength()*lap)).append(System.lineSeparator());

        List<Car> result = super.getParticipants().stream().sorted((c1, c2) -> Integer.compare(c2.getOverallPerformance(), c1.getOverallPerformance())).limit(4).collect(Collectors.toList());
        for (Car car : result) {
            sb.append(String.format("%d. %s %s %dPP - $%d", position, car.getBrand(), car.getModel(), car.getOverallPerformance(), positionPrize.get(position)))
                    .append(System.lineSeparator());
            position++;
        }

        return sb.toString().trim();
    }
}
