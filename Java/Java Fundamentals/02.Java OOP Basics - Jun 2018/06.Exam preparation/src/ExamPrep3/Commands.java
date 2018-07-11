package ExamPrep3;

import ExamPrep3.benders.*;
import ExamPrep3.monuments.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Commands {

    private Nation airNation;
    private Nation fireNation;
    private Nation earthNation;
    private Nation waterNation;
    private List<String> wars;

    public Commands() {
        this.wars = new ArrayList<>();
        this.airNation = new Nation("Air");
        this.fireNation = new Nation("Fire");
        this.waterNation = new Nation("Water");
        this.earthNation = new Nation("Earth");
    }

    public void bender(String type, String name, int power, double secondaryParameter) {
        Bender newBender = null;
        switch (type) {
            case "Air":
                newBender = new AirBender(name, power, secondaryParameter);
                this.airNation.addBenders(newBender);
                break;
            case "Water":
                newBender = new WaterBender(name, power, secondaryParameter);
                this.waterNation.addBenders(newBender);
                break;
            case "Fire":
                newBender = new FireBender(name, power, secondaryParameter);
                this.fireNation.addBenders(newBender);
                break;
            case "Earth":
                newBender = new EarthBender(name, power, secondaryParameter);
                this.earthNation.addBenders(newBender);
                break;
        }
    }

    public void monument(String type, String name, int affinity) {
        Monument newMonument = null;
        switch (type) {
            case "Air":
                newMonument = new AirMonument(name, affinity);
                this.airNation.addMonument(newMonument);
                break;
            case "Water":
                newMonument = new WaterMonument(name, affinity);
                this.waterNation.addMonument(newMonument);
                break;
            case "Fire":
                newMonument = new FireMonument(name, affinity);
                this.fireNation.addMonument(newMonument);
                break;
            case "Earth":
                newMonument = new EarthMonument(name, affinity);
                this.earthNation.addMonument(newMonument);
                break;
        }
    }

    public void status(String nation) {
        switch (nation) {
            case "Air":
                System.out.println(airNation.toString());
                break;
            case "Water":
                System.out.println(waterNation.toString());
                break;
            case "Fire":
                System.out.println(fireNation.toString());
                break;
            case "Earth":
                System.out.println(earthNation.toString());
                break;
        }
    }

    public void war(String nation) {
        wars.add(nation);
        Map<String, Double> orderedNationByPower = new LinkedHashMap<>();

        double totalPowerAirNation = airNation.getTotalPower();
        double totalPowerFireNation = fireNation.getTotalPower();
        double totalPowerWaterNation = waterNation.getTotalPower();
        double totalPowerEarthNation = earthNation.getTotalPower();

        orderedNationByPower.put("Air", totalPowerAirNation);
        orderedNationByPower.put("Fire", totalPowerFireNation);
        orderedNationByPower.put("Water", totalPowerWaterNation);
        orderedNationByPower.put("Earth", totalPowerEarthNation);

        List<String> result = new ArrayList<>();

        orderedNationByPower.entrySet().stream().sorted((a, b) -> Double.compare(b.getValue(), a.getValue())).limit(1).forEach(e -> {
            result.add(e.getKey());
        });
        String winnerNation = result.get(0);
        switch (winnerNation) {
            case "Air":
                fireNation.setBenders(new ArrayList<>());
                waterNation.setBenders(new ArrayList<>());
                earthNation.setBenders(new ArrayList<>());

                fireNation.setMonuments(new ArrayList<>());
                waterNation.setMonuments(new ArrayList<>());
                earthNation.setMonuments(new ArrayList<>());
                break;
            case "Water":
                fireNation.setBenders(new ArrayList<>());
                airNation.setBenders(new ArrayList<>());
                earthNation.setBenders(new ArrayList<>());

                fireNation.setMonuments(new ArrayList<>());
                airNation.setMonuments(new ArrayList<>());
                earthNation.setMonuments(new ArrayList<>());
                break;
            case "Fire":
                airNation.setBenders(new ArrayList<>());
                waterNation.setBenders(new ArrayList<>());
                earthNation.setBenders(new ArrayList<>());

                airNation.setMonuments(new ArrayList<>());
                waterNation.setMonuments(new ArrayList<>());
                earthNation.setMonuments(new ArrayList<>());
                break;
            case "Earth":
                fireNation.setBenders(new ArrayList<>());
                waterNation.setBenders(new ArrayList<>());
                airNation.setBenders(new ArrayList<>());

                fireNation.setMonuments(new ArrayList<>());
                waterNation.setMonuments(new ArrayList<>());
                airNation.setMonuments(new ArrayList<>());
                break;
        }
    }

    public void quit() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.wars.size(); i++) {
            result.append(String.format("War %d issued by %s", (i + 1), wars.get(i)))
                    .append(System.lineSeparator());
        }
        System.out.println(result.toString().trim());
    }
}
