package p06FootballTeamGenerator_66points;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private double rating;
    private List<Player> players;

    public Team(String name) {
        this.setName(name);
        this.players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    /*public Double getRating() {
        if (this.players.size() != 0){
            this.rating = this.players.stream().mapToDouble(Player::getOverallSkillLevel).sum() / players.size();
        }
        return this.rating;
    }*/
    public Long getRating() {
        double total = 0.0;
        for (Player player : players) {
            total += Math.round(player.getOverallSkillLevel());
        }

        if (total == 0) {
            return 0L;
        } else {
            return Math.round(total / players.size());
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        if (!players.contains(player) || player == null) {
            throw new IllegalArgumentException("Player " + player.getName() + " is not in " + this.name + " team.");
        }
        players.remove(player);
    }

}
