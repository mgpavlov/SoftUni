package p06FootballTeamGenerator2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, Team> teamsByName = new HashMap<>();

        while (true) {
            String command = scan.nextLine();
            if (command.equals("END")){
                break;
            }

            String[] commandArgs = command.split(";");
            String commandType = commandArgs[0];

            try {

                if (commandType.equals("Team")) {
                    String teamName = commandArgs[1];
                    Team team = new Team(teamName);
                    teamsByName.put(teamName, team);

                } else if (commandType.equals("Add")) {
                    String teamName = commandArgs[1];
                    String playerName = commandArgs[2];
                    if (!teamsByName.containsKey(teamName)) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        int endurance = Integer.parseInt(commandArgs[3]);
                        int sprint = Integer.parseInt(commandArgs[4]);
                        int dribble = Integer.parseInt(commandArgs[5]);
                        int passing = Integer.parseInt(commandArgs[6]);
                        int shooting = Integer.parseInt(commandArgs[7]);

                        Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);

                        Team team = teamsByName.get(teamName);
                        team.addPlayer(player);
                    }

                } else if (commandType.equals("Remove")) {
                    String teamName = commandArgs[1];
                    String playerName = commandArgs[2];
                    if (!teamsByName.containsKey(teamName)) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        Team team = teamsByName.get(teamName);

                        team.removePlayer(playerName);
                    }
                  
                } else if (commandType.equals("Rating")) {
                    String teamName = commandArgs[1];
                    if (!teamsByName.containsKey(teamName)) {
                        System.out.printf("Team %s does not exist.%n", teamName);
                    } else {
                        Team team = teamsByName.get(teamName);

                        System.out.printf("%s - %d%n", team.getName(), team.getRating());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
