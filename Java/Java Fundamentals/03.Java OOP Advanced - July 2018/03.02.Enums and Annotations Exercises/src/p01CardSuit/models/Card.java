package p01CardSuit.models;

import p01CardSuit.enums.Ranks;
import p01CardSuit.enums.Suits;

public class Card implements Comparable<Card>{
    private String rank;
    private String suit;
    private int power;

    public Card(String rank, String suit) {
        this.setRank(rank);
        this.setSuit(suit);
        this.setPower(Enum.valueOf(Ranks.class, rank).getPower() + Enum.valueOf(Suits.class, suit).getPower());
    }

    private void setRank(String rank) {
        this.rank = rank;
    }

    private void setSuit(String suit) {
        this.suit = suit;
    }

    private void setPower(int power) {
        this.power = power;
    }

    public String getRank() {
        return this.rank;
    }

    public String getSuit() {
        return this.suit;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", this.getRank(), this.getSuit(), this.getPower());
    }

    @Override
    public int compareTo(Card card2) {
        return Integer.compare(this.getPower(), card2.getPower());
    }
}
