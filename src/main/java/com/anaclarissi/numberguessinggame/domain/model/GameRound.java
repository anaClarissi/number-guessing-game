package com.anaclarissi.numberguessinggame.domain.model;

import com.anaclarissi.numberguessinggame.domain.exception.OutOfAttemptsException;
import com.anaclarissi.numberguessinggame.domain.service.GuessEvaluator;

public class GameRound {

    private final SecretNumber secretNumber;

    private final Difficulty difficulty;

    private int attemptsUsed;

    private boolean finished;

    private boolean won;

    private final GuessEvaluator guessEvaluator;

    public GameRound(SecretNumber secretNumber, Difficulty difficulty) {

        this.secretNumber = secretNumber;
        this.difficulty = difficulty;

        this.attemptsUsed = 0;
        this.finished = false;
        this.won = false;

        guessEvaluator = new GuessEvaluator();

    }

    public GuessResult registerGuess(int value) {

        if (finished) throw new OutOfAttemptsException("The game round was already finished.");

        setAttemptsUsed(getAttemptsUsed() + 1);

        Guess guess = new Guess(value, getAttemptsUsed());

        GuessResult result = guessEvaluator.evaluate(secretNumber, guess);

        if (result == GuessResult.CORRECT) {

            setWon(true);

            setFinished(true);

        } else if (getAttemptsUsed() == difficulty.getMaxAttempts()) {

            setFinished(true);

        }

        return result;

    }

    public boolean isWon() {

        return this.won;

    }

    private void setWon(boolean won) {
        this.won = won;
    }

    public boolean isOver() {
        return this.finished;
    }

    private void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getAttemptsUsed() {
        return this.attemptsUsed;
    }

    private void setAttemptsUsed(int attemptUsed) {
        this.attemptsUsed = attemptUsed;
    }

}
