package callofduty.domain.missions;

public class SurveillanceMission extends AbstractMission {
    public SurveillanceMission(String id, double rating, double bounty) {
        super(id,  rating*0.25, bounty*1.50);
    }
    @Override
    public String toString() {
        return "Surveillance"+super.toString();
    }
}
