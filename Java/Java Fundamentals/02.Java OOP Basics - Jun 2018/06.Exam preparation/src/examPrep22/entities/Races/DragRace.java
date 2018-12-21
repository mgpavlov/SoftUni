package examPrep22.entities.Races;

import examPrep22.entities.Cars.Car;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class DragRace extends Race {
    public DragRace(int length, String route, int prizePool) {
        super(length, route, prizePool);
    }

    @Override
    public String toString() {
        int position = 1;
        Map<Integer, Integer> positionPrize = new TreeMap<>();
        positionPrize.put(1, (super.getPrizePool() * 50) / 100);
        positionPrize.put(2, (super.getPrizePool() * 30) / 100);
        positionPrize.put(3, (super.getPrizePool() * 20) / 100);
        StringBuilder sb = new StringBuilder(super.toString());

        List<Car> result = super.getParticipants().stream().sorted((c1, c2) -> Integer.compare(c2.getEnginePerformance(), c1.getEnginePerformance())).limit(3).collect(Collectors.toList());
        for (Car car : result) {
            sb.append(String.format("%d. %s %s %dPP - $%d", position, car.getBrand(), car.getModel(), car.getEnginePerformance(), positionPrize.get(position)))
                    .append(System.lineSeparator());
            position++;
        }

        return sb.toString().trim();
    }
}