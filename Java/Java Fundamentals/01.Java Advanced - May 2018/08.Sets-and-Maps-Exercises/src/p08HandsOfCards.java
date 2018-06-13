import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class p08HandsOfCards {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> cardsValue = new LinkedHashMap<>();

        cardsValue.put('2', 2);
        cardsValue.put('3', 3);
        cardsValue.put('4', 4);
        cardsValue.put('5', 5);
        cardsValue.put('6', 6);
        cardsValue.put('7', 7);
        cardsValue.put('8', 8);
        cardsValue.put('9', 9);
        cardsValue.put('J', 11);
        cardsValue.put('Q', 12);
        cardsValue.put('K', 13);
        cardsValue.put('A', 14);

        Map<Character, Integer> colourValue = new LinkedHashMap<>();
        colourValue.put('S', 4);
        colourValue.put('H', 3);
        colourValue.put('D', 2);
        colourValue.put('C', 1);

        Map<String, HashSet<String>> players = new LinkedHashMap<>();

        while (true) {
            String input = reader.readLine();
            if (input.equals("JOKER")){
                break;
            }

            String[] data = input.split(":");
            String name = data[0].trim();
            if (!players.containsKey(name)){
                players.put(name, new HashSet<>());
            }
            String[] cards = data[1].trim().split(",");

            for (int i = 0; i < cards.length; i++) {
                String currentCard = cards[i].trim();
                players.get(name).add(currentCard);
            }
        }

        for (String player : players.keySet()) {

            int playerSum = 0;
            for (String card : players.get(player)) {
                if (card.startsWith("10")){
                    playerSum+=10*colourValue.get(card.charAt(2));
                }else {
                    playerSum+=cardsValue.get(card.charAt(0))*colourValue.get(card.charAt(1));
                }
            }

            System.out.println(player + ": " + playerSum);
        }
    }
}
