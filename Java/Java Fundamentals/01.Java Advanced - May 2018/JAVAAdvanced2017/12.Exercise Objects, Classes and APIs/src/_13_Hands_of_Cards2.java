import java.util.HashSet;
import java.util.LinkedHashMap;  // 100/100
import java.util.Map;
import java.util.Scanner;

public class _13_Hands_of_Cards2 {
    public static void main(String[] args) {

        LinkedHashMap<String, HashSet<String>> players = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();

        while (!command.equals("JOKER")) {
            String[] tokens = command.split(":");
            String name = tokens[0].trim();

            if (!players.containsKey(name)) {
                players.put(name, new HashSet<>());
            }

            String[] cards = tokens[1].trim().split(",");

            for (String card : cards) {
                String currentCard = card.trim();
                players.get(name).add(currentCard);
            }

            command = sc.nextLine();
        }

        for (Map.Entry<String, HashSet<String>> pair : players.entrySet()) {
            String currentPlayer = pair.getKey();
            Integer points = calculatePoints(pair.getValue());
            System.out.printf("%s: %s%n", currentPlayer, points);
        }
    }

    private static Integer calculatePoints(HashSet<String> cards) {
        Integer points = 0;

        for (String card : cards) {
            String power = card.substring(0, card.length() - 1);
            char multiplier = card.charAt(card.length() - 1);

            Integer cardPoints = getPower(power) * getMultiplier(multiplier);
            points += cardPoints;
        }

        return points;
    }

    private static Integer getMultiplier(char multiplier) {
        switch (multiplier) {
            case 'S':
                return 4;
            case 'H':
                return 3;
            case 'D':
                return 2;
            case 'C':
                return 1;
            default:
                return 0;
        }
    }

    private static Integer getPower(String power) {
        switch (power) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
                return 10;
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return 0;
        }
    }
}
