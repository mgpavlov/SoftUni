package p08CardGame2.enums;

import p08CardGame2.annotations.EnumAnnotation;

@EnumAnnotation(type = "Enumeration", category = "Suit", description = "Provides suit constants for a Card class.")
public enum CardSuit {
    CLUBS(0), DIAMONDS(13), HEARTS(26), SPADES(39);

    private int power;

    CardSuit(int power) {
        this.setPower(power);
    }

    public int getPower() {
        return this.power;
    }

    private void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return super.name();
    }
}
