package com.softuni.residentevil.domain.enums;

public enum Magnitude {
    LOW, MEDIUM, HIGH;

    public static Magnitude fromNormalizedName(final String normalizedName) {
        return valueOf(normalizedName.toUpperCase());
    }

    public String getNormalizedName() {
        return this.name().substring(0, 1)
                + this.name().substring(1).toLowerCase();
    }
}
