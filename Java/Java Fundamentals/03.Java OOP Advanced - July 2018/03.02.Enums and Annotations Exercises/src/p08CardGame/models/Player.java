package p08CardGame.models;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Player {
    private String name;
    private Set<Card> hand;
    public Player(String name) {
        this.setName(name);
        hand = new LinkedHashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void addCard(Card card) {
        this.hand.add(card);
    }

    public Set<Card> getHand() {
        return this.hand;
    }
}
