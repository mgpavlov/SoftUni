import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.util.*;

public class _13_Hands_of_Cards
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, HashSet<String>> players = new LinkedHashMap<>();

        enterPlayersAndCards(bf, players);
        for (Map.Entry<String, HashSet<String>> player : players.entrySet()) {
            String name = player.getKey();
            Set<String> playerCards = player.getValue();

            int sumCardPoints = 0;
            for (String playerCard : playerCards) {
                sumCardPoints += calcCardValue(playerCard);
            }

            System.out.printf("%s: %d%n", name, sumCardPoints);
        }

        System.out.println();
    }

    private static void enterPlayersAndCards(BufferedReader bf, Map<String, HashSet<String>> players) throws IOException {
        while (true) {
            String[] line = bf.readLine().split(":\\s+");

            if ("JOKER".equalsIgnoreCase(line[0])) {
                break;
            }

            String playerName = line[0];
            String[] cards = line[1].split(",\\s+");

            if (!players.containsKey(playerName)) {
                players.put(playerName, new HashSet<>());
            }

            HashSet<String> cardsDistinct = new HashSet<>(players.get(playerName));
            cardsDistinct.addAll(Arrays.asList(cards));
            players.put(playerName, cardsDistinct);
        }
    }

    private static int calcCardValue(String card) {
        Map<String, Integer> cardPower = new HashMap<>();
        Map<String, Integer> cardType = new HashMap<>();

        for (int i = 2; i <= 10; i++) {
            cardPower.put(Integer.toString(i), i);
        }

        cardPower.put("J", 11);
        cardPower.put("Q", 12);
        cardPower.put("K", 13);
        cardPower.put("A", 14);

        cardType.put("C", 1);
        cardType.put("D", 2);
        cardType.put("H", 3);
        cardType.put("S", 4);

        String currentCardPower = card.substring(0, card.length() - 1);
        String currentCardType = card.substring(card.length() - 1);
        int currentCardPoints = cardPower.get(currentCardPower) * cardType.get(currentCardType);

        return currentCardPoints;
    }
}
