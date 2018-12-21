package p01CardSuit.enums;

public enum Suits {
    CLUBS(0), DIAMONDS(13), HEARTS(26), SPADES(39);
    private int power;

    Suits(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    @Override
    public String toString() {
        return String.format("Ordinal value: %d; Name value: %s", this.ordinal(), this.name());
    }
}
