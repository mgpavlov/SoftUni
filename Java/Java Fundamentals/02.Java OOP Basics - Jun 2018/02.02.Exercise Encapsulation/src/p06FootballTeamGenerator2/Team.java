package p06FootballTeamGenerator2;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Player> players;
    private String name;
    private Double rating;

    public Team(String teamName) {
        this.setName(teamName);
        this.setPlayers(new ArrayList<>());
    }

    private void setName(String name) {
        this.name = name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(String playerName) throws Exception {
        Player playerToBeRemoved = null;
        for (Player player : this.getPlayers()) {
            if (player.getName().equals(playerName)) {
                playerToBeRemoved = player;
                break;
            }
        }
        if (playerToBeRemoved == null) {
            throw new Exception(String.format("Player %s is not in %s team.", playerName, this.getName()));
        }
        this.getPlayers().remove(playerToBeRemoved);
    }

    private void setPlayers(List<Player> players) {
        this.players = players;
    }

    public String getName() {
        return this.name;
    }

    public Long getRating() {
        double total = 0.0;
        for (Player player : players) {
            total += Math.round(player.getStatus());
        }

        if (total == 0) {
            return 0L;
        } else {
            return Math.round(total / players.size());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
