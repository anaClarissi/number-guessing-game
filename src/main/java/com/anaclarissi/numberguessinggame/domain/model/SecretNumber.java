package com.anaclarissi.numberguessinggame.domain.model;

public class SecretNumber {

    private final int value;

    public SecretNumber(int value) {

        if (value < 1 || value > 100) throw new IllegalArgumentException("Invalid Value: " + value);

        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean isEqualTo(int guess) {

        return getValue() == guess;

    }

}
