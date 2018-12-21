package p08CardGame2;

import p08CardGame2.enums.CardRank;
import p08CardGame2.enums.CardSuit;
import p08CardGame2.models.Card;
import p08CardGame2.models.Deck;
import p08CardGame2.models.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Deck deck = new Deck();
        deck.generateDeck();

        Player firstPlayer = new Player(reader.readLine());
        Player secondPlayer = new Player(reader.readLine());

        getPlayerCards(reader, deck, firstPlayer);
        getPlayerCards(reader, deck, secondPlayer);

        if (firstPlayer.compareTo(secondPlayer) > 0) {
            System.out.println(firstPlayer);
        } else {
            System.out.println(secondPlayer);
        }
    }

    private static void getPlayerCards(BufferedReader reader, Deck deck, Player player) throws IOException {
        while (player.getCardsCount() < 5) {
            String[] cardArgs = reader.readLine().split(" of ");
            try {
                CardRank cardRank = CardRank.valueOf(cardArgs[0]);
                CardSuit cardSuit = CardSuit.valueOf(cardArgs[1]);

                Card card = new Card(cardRank, cardSuit);
                try {
                    deck.removeCard(card);
                    player.addCard(card);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("No such card exists.");
            }
        }
    }
}
