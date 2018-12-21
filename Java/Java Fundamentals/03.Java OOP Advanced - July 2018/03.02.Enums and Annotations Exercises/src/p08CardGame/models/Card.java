package p08CardGame.models;

import p08CardGame.enums.Ranks;
import p08CardGame.enums.Suits;

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
        try{
            this.rank = Ranks.valueOf(Ranks.class, rank).name();
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("No such card exists.");
        }

    }

    private void setSuit(String suit) {
        try{
            this.suit = Suits.valueOf(Suits.class, suit).name();
        }catch (IllegalArgumentException ex){
            throw new IllegalArgumentException("No such card exists.");
        }
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
        return String.format("%s of %s", this.getRank(), this.getSuit());
    }

    @Override
    public int compareTo(Card card2) {
        return Integer.compare(this.getPower(), card2.getPower());
    }
}
