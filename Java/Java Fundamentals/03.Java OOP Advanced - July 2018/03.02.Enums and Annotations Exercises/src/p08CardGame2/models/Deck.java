package p08CardGame2.models;

import p08CardGame2.enums.CardRank;
import p08CardGame2.enums.CardSuit;

import java.util.HashSet;
import java.util.Set;

public class Deck {
    private Set<Card> deck;

    public Deck() {
        this.deck = new HashSet<>();
    }

    public void generateDeck() {
        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                Card card = new Card(cardRank, cardSuit);
                deck.add(card);
            }
        }
    }

    public void removeCard(Card card) {
        if (!this.deck.contains(card)) {
            throw new IllegalArgumentException("Card is not in the deck.");
        }

        this.deck.remove(card);
    }
}
