package p08CardGame.models;

import p08CardGame.enums.Ranks;
import p08CardGame.enums.Suits;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Deck {

    private Map<String, Card> deckOfCards;

    public Deck() {
        this.deckOfCards = new HashMap<>();
        this.setDeckOfCards();
    }

    public void setDeckOfCards() {
        for (Suits suit : Suits.values()) {
            for (Ranks rank : Ranks.values()) {
                Card card = new Card(rank.name(), suit.name());
                this.deckOfCards.put(card.toString(), card);
            }
        }
    }

    public void removeCard(Card card){
        if (!this.deckOfCards.containsKey(card.toString())){
            throw new IllegalArgumentException("Card is not in the deck.");
        }
        this.deckOfCards.remove(card.toString());
    }
}
