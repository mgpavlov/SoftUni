package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;

public class Master extends AbstractAgent implements BountyAgent {
    private double bounty;

    public Master(String id, String name, Double rating) {
        super(id, name, rating);
        this.bounty = 0;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator()).append(String.format("Bounty Earned: $%f", this.getBounty()));
        return sb.toString();
    }
}
