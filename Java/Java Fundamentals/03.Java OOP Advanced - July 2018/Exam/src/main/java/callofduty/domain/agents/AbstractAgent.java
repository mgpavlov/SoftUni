package callofduty.domain.agents;

import callofduty.domain.missions.AbstractMission;
import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractAgent implements Agent {
    private String id;
    private String name;
    private double rating;

    private Map<String, Mission> assignedMissions;
    private Map<String, Mission> completedMissions;

    protected AbstractAgent(String id, String name, double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.assignedMissions = new LinkedHashMap<>();
        this.completedMissions = new LinkedHashMap<>();
    }

    @Override
    public void acceptMission(Mission mission) throws NoSuchFieldException {
        assignedMissions.put(mission.getId(), mission);

    }

    @Override
    public void completeMissions() throws NoSuchFieldException, IllegalAccessException {
        for (Mission mission : assignedMissions.values()) {
            Field isComplete = AbstractMission.class.getDeclaredField("isComplete");
            isComplete.setAccessible(true);
            boolean currentIsComplete = (boolean) isComplete.get(mission);
            if (!currentIsComplete) {
                this.completedMissions.put(mission.getId(), mission);
                isComplete.set(mission, true);
            }
        }
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%s Agent – %s", this.getClass().getSimpleName(), this.getName())).append(System.lineSeparator())
                .append(String.format("Personal Code: %s", this.getId())).append(System.lineSeparator())
                .append(String.format("Assigned Missions: %d", this.assignedMissions.size())).append(System.lineSeparator())
                .append(String.format("Completed Missions: %d", this.completedMissions.size())).append(System.lineSeparator())
                .append(String.format("Rating: %f", this.getRating()));
        return sb.toString();
    }
}
