package callofduty.domain.missions;

public class HuntMission extends AbstractMission {
    public HuntMission(String id, double rating, double bounty) {
        super(id, (double)rating*1.50, (double)bounty*2.00);
    }
    @Override
    public String toString() {
        return "Hunt"+super.toString();
    }
}
