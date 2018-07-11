package p06FootballTeamGenerator2;

public class Player {
    private String name;
    private double status;
    private int endurance;
    private int sprint;
    private int dribble;
    private int passing;
    private int shooting;


    public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
        this.setName(name);
        this.setEndurance(endurance);
        this.setSprint(sprint);
        this.setDribble(dribble);
        this.setPassing(passing);
        this.setShooting(shooting);
        this.setStatus();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    private void setSprint(int sprint) {
        isValidStat(sprint, "Sprint");
        this.sprint = sprint;
    }

    private void setDribble(int dribble) {
        isValidStat(dribble, "Dribble");
        this.dribble = dribble;
    }

    private void setPassing(int passing) {
        isValidStat(endurance, "Passing");
        this.passing = passing;
    }

    private void setShooting(int shooting) {
        isValidStat(shooting, "Shooting");
        this.shooting = shooting;
    }

    private void setEndurance(int endurance) {
        isValidStat(endurance, "Endurance");
        this.endurance = endurance;
    }

    private void isValidStat(int stat, String statName) {
        if (stat < 0 || stat > 100) {
            throw new IllegalArgumentException(
                    String.format("%s should be between 0 and 100.", statName));
        }
    }

    public String getName() {
        return this.name;
    }

    private void setStatus() {
        double total = 0.0;
        total += this.getEndurance();
        total += this.getSprint();
        total += this.getDribble();
        total += this.getPassing();
        total += this.getShooting();

        this.status = total / 5.0;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getSprint() {
        return sprint;
    }

    public int getDribble() {
        return dribble;
    }

    public int getPassing() {
        return passing;
    }

    public int getShooting() {
        return shooting;
    }

    public double getStatus() {
        return this.status;
    }
}
