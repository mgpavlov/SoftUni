package callofduty.domain.missions;

public class EscortMission extends AbstractMission {
    public EscortMission(String id, double rating, double bounty) {
        super(id, (double)rating*0.75, (double)bounty*1.25);
    }

    @Override
    public String toString() {
        return "Escort"+super.toString();
    }
}
