package p06FootballTeamGenerator_66points;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Team> teams = new LinkedHashMap<>();
        Map<String, Map<String, Player>> teamPlayers = new LinkedHashMap<>();

        while (true) {
            String[] input = reader.readLine().split(";");
            if ("END".equals(input[0])) {
                break;
            }
            if (input[0].equalsIgnoreCase("Team")) {
                String teamName = input[1];
                Team team = null;
                try {
                    team = new Team(teamName);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }
                teams.put(teamName, team);
            } else if (input[0].equalsIgnoreCase("Add")) {
                String teamName = input[1];
                String playerName = input[2];
                int endurance = Integer.parseInt(input[3]);
                int sprint = Integer.parseInt(input[4]);
                int dribble = Integer.parseInt(input[5]);
                int passing = Integer.parseInt(input[6]);
                int shooting = Integer.parseInt(input[7]);
                Player player = null;
                try {
                    player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                    continue;
                }

                if (teams.containsKey(teamName)) {
                    teams.get(teamName).addPlayer(player);
                }else {
                    System.out.printf("Team %s does not exist.%n", teamName);
                }
                teamPlayers.putIfAbsent(teamName, new LinkedHashMap<>());
                teamPlayers.get(teamName).put(playerName, player);
            } else if (input[0].equalsIgnoreCase("Remove")) {
                String teamName = input[1];
                String playerName = input[2];
                if (teams.containsKey(teamName)) {
                    if (teamPlayers.get(teamName).containsKey(playerName)){
                        try{
                            teams.get(teamName).removePlayer(teamPlayers.get(teamName).get(playerName));
                        }catch (IllegalArgumentException ex){
                            System.out.println(ex.getMessage());
                        }
                    }else {
                        System.out.println("Player " + playerName + " is not in " + teamName + " team.");
                    }

                }
                else {
                    System.out.printf("Team %s does not exist.", teamName);
                }
            } else if (input[0].equalsIgnoreCase("Rating")) {
                String teamName = input[1];
                if (!teams.containsKey(teamName)){
                    System.out.printf("Team %s does not exist.", teamName);
                    continue;
                }
                System.out.printf("%s - %d%n",teamName, Math.round(teams.get(teamName).getRating()));
            }
        }
    }
}