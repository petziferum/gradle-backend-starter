package com.petziferum.gradlebackend.bauwerk;

public enum Materialart {
    BETON("Beton"),
    GLAS("Glas");

    private final String anzeigetext;

    Materialart(String anzeigetext) {
        this.anzeigetext = anzeigetext;
    }

    @Override
    public String toString() {
        return anzeigetext;
    }

    public String getAnzeigetext() {
        return anzeigetext;
    }
}
