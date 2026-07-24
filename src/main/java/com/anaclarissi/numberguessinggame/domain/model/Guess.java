package com.anaclarissi.numberguessinggame.domain.model;

import com.anaclarissi.numberguessinggame.domain.exception.InvalidGuessException;

public class Guess {

    private final int value;

    private final int attemptNumber;

    public Guess(int value, int attemptNumber) {

        if ((value < 1 || value > 100) || attemptNumber < 1) throw new InvalidGuessException("Invalid Guess value or attempt number");

        this.value = value;
        this.attemptNumber = attemptNumber;
    }

    public int getValue() {
        return value;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

}
