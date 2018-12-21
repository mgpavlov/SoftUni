package callofduty.domain.missions;

import callofduty.interfaces.Mission;

public abstract class AbstractMission implements Mission {
    private double bounty;
    private String id;
    private double rating;
    private boolean isComplete;

    protected AbstractMission(String id, double rating, double bounty) {
        this.bounty = bounty;
        this.id = id;
        this.rating = rating;
        this.isComplete = false;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    /*{missionType} Mission – {id}
    Status: {Open / Completed}
    Rating: {rating}
    Bounty: {bounty}
    */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" Mission – %s", this.getId())).append(System.lineSeparator())
                .append(String.format("Status: %s", this.isComplete)).append(System.lineSeparator())
                .append(String.format("Rating: %f", this.getRating())).append(System.lineSeparator())
                .append(String.format("Bounty: %f", this.getBounty()));

        return sb.toString();
    }
}
