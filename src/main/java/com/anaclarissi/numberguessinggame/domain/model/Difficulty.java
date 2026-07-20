package com.anaclarissi.numberguessinggame.domain.model;

public enum Difficulty {

    EASY(10, "easy"),
    MEDIUM(5, "medium"),
    HARD(3, "hard");

    private final int maxAttempts;

    private final String label;

    Difficulty(int maxAttempts, String label) {
        this.maxAttempts = maxAttempts;
        this.label = label;
    }

    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    public String getLabel() {
        return this.label;
    }

}
