package com.anaclarissi.numberguessinggame.domain.model;

public class ScoreRecord {

    private final Difficulty difficulty;

    private final int bestAttempts;

    public ScoreRecord(Difficulty difficulty, int bestAttempts) {

        if (difficulty == null) throw new IllegalArgumentException("Invalid difficulty value!");

        if (bestAttempts < 1 || bestAttempts > difficulty.getMaxAttempts()) throw new IllegalArgumentException("The best attempts should be between 1 and " + difficulty.getMaxAttempts());

        this.difficulty = difficulty;
        this.bestAttempts = bestAttempts;

    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getBestAttempts() {
        return bestAttempts;
    }

}
