package p08MilitaryElite2;

import java.util.ArrayList;
import java.util.List;

public class Commando extends SpecialisedSoldier implements ICommando {
    private List<Mission> missions;

    public Commando(String id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.missions = new ArrayList<>();
    }

    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public List<Mission> getMissions() {
        return this.missions;
    }

    @Override
    public void CompleteMission() {

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Name: %s %s Id: %s Salary: %.2f%nCorps: %s%nMissions:%n", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary(), this.getCorps()));
        for (Mission mission : this.getMissions()) {
            sb.append(mission.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}