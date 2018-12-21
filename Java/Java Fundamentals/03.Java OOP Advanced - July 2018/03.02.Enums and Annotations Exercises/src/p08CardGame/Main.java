package p08CardGame;
//60/100
import p08CardGame.models.Card;
import p08CardGame.models.Deck;
import p08CardGame.models.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Deck deck = new Deck();
        String firstPlayerName = reader.readLine();
        String secondPlayerName = reader.readLine();

        Player firstPlayer = new Player(firstPlayerName);
        Player secondPlayer = new Player(secondPlayerName);

        addCards(reader, firstPlayer, deck);
        addCards(reader, secondPlayer, deck);
        Card card1 = getMostPowerfulCard(firstPlayer.getHand());
        Card card2 = getMostPowerfulCard(secondPlayer.getHand());

        if (card1.compareTo(card2) > 0) {
            System.out.printf("%s wins with %s.", firstPlayer.getName(), card1.toString());
        } else {
            System.out.printf("%s wins with %s.", secondPlayer.getName(), card2.toString());
        }
    }

    private static Card getMostPowerfulCard(Set<Card> playerCards) {
        return playerCards.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getPower(), c1.getPower()))
                .collect(Collectors.toList()).get(0);
    }


    private static void addCards(BufferedReader reader, Player player, Deck deck) throws IOException {
        while (player.getHand().size() < 5) {
            String input = reader.readLine();
            try {
                String rank = "";
                String suit = "";
                try {
                    rank = input.split(" of ")[0];
                    suit = input.split(" of ")[1];
                } catch (IllegalArgumentException ex) {
                    System.out.println("No such card exists.");
                    continue;
                }
                Card card = new Card(rank, suit);
                deck.removeCard(card);
                player.addCard(card);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
        }
    }
}
