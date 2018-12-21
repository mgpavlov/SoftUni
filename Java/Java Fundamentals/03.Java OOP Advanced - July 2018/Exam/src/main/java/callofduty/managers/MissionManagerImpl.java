package callofduty.managers;

import callofduty.domain.agents.AbstractAgent;
import callofduty.domain.agents.Master;
import callofduty.domain.agents.Novice;
import callofduty.interfaces.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MissionManagerImpl implements MissionManager {
    private MissionControl controller;
    private OutputWriter writer;


    private Map<String, Mission> missions;
    private Map<String, Agent> agents;
    private Map<String, Identifiable> allIdentifiable;

    public MissionManagerImpl(MissionControl controller, OutputWriter writer) {
        this.controller = controller;
        this.writer = writer;
        this.agents = new LinkedHashMap<>();
        this.missions = new LinkedHashMap<>();
        this.allIdentifiable = new LinkedHashMap<>();

    }

    @Override
    public String agent(List<String> arguments) {
        String id = arguments.get(0);
        String name = arguments.get(1);
        Agent agent = new Novice(id, name);
        this.agents.put(id, agent);
        this.allIdentifiable.put(id, agent);
        return String.format("Registered Agent - %s:%s", name, id);
    }

    @Override
    public String request(List<String> arguments) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String agentId = arguments.get(0);
        Agent currentAgent = this.agents.get(agentId);

        String missionId = arguments.get(1);
        double missionRating = Double.parseDouble(arguments.get(2));
        double missionBounty = Double.parseDouble(arguments.get(3));
        String agentName = currentAgent.getName();
        Mission currentMission = this.controller.generateMission(missionId, missionRating, missionBounty);
        String missionType = currentMission.getClass().getSimpleName();
        this.missions.put(missionId, currentMission);
        this.allIdentifiable.put(missionId, currentMission);
        currentAgent.acceptMission(currentMission);
        return String.format("Assigned %s Mission - %s to Agent - %s", missionType, missionId, agentName);

    }

    @Override
    public String complete(List<String> arguments) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        String id = arguments.get(0);
        Agent currentAgent = this.agents.get(id);
        String name = currentAgent.getName();
        Field completedMissionsField = AbstractAgent.class.getDeclaredField("completedMissions");
        completedMissionsField.setAccessible(true);
        Map<String, Mission> completedMissions = (Map<String, Mission>) completedMissionsField.get(currentAgent);


        Field rating = AbstractAgent.class.getDeclaredField("rating");
        double ratingValue = currentAgent.getRating();
        double newRating = ratingValue + completedMissions.entrySet().stream().mapToDouble(m->m.getValue().getRating()).sum();
        rating.setAccessible(true);
        rating.set(currentAgent, newRating);

        if (currentAgent.getClass().getSimpleName().equals("Master")){
            Field bounty = currentAgent.getClass().getDeclaredField("bounty");
            double bountyValue = (double) bounty.get(currentAgent);
            double newBounty = bountyValue + completedMissions.entrySet().stream().mapToDouble(m->m.getValue().getBounty()).sum();
            bounty.set(currentAgent, newBounty);
        }

        if (completedMissions.size() >=3){
            Agent newAgent = new Master(currentAgent.getId(), name, currentAgent.getRating());
            Field completedMissionsNewField = AbstractAgent.class.getDeclaredField("completedMissions");
            completedMissionsNewField.setAccessible(true);
            completedMissionsNewField.set(newAgent, completedMissions);
            agents.put(id, newAgent);
        }
        currentAgent.completeMissions();



        return String.format("Agent - %s:%s has completed all assigned missions", name, id);


    }

    @Override
    public String status(List<String> arguments) {
        String id = arguments.get(0);
        return this.allIdentifiable.get(id).toString();
    }

    @Override
    public String over(List<String> arguments) {
        return null;
    }
}
