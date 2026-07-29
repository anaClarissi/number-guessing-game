package com.anaclarissi.numberguessinggame.domain.model;

import com.anaclarissi.numberguessinggame.domain.exception.OutOfAttemptsException;
import com.anaclarissi.numberguessinggame.domain.service.GameClockPort;
import com.anaclarissi.numberguessinggame.domain.service.GuessEvaluator;

import java.time.Duration;

public class GameRound {

    private final SecretNumber secretNumber;
    private final Difficulty difficulty;
    private final GuessEvaluator guessEvaluator;
    private final GameClockPort clock;

    private int attemptsUsed;
    private Duration elapsedTime;

    private boolean finished;
    private boolean won;

    public GameRound(SecretNumber secretNumber, Difficulty difficulty, GameClockPort clock) {

        this.secretNumber = secretNumber;
        this.difficulty = difficulty;
        this.clock = clock;

        this.attemptsUsed = 0;
        this.finished = false;
        this.won = false;

        guessEvaluator = new GuessEvaluator();

        this.clock.start();

    }

    public GuessResult registerGuess(int value) {

        if (finished) throw new OutOfAttemptsException("The game round was already finished.");

        setAttemptsUsed(getAttemptsUsed() + 1);

        Guess guess = new Guess(value, getAttemptsUsed());

        GuessResult result = guessEvaluator.evaluate(secretNumber, guess);

        if (result == GuessResult.CORRECT) {

            setWon(true);

        }

        if (result == GuessResult.CORRECT || getAttemptsUsed() == difficulty.getMaxAttempts()) {

            setFinished(true);

            this.elapsedTime = clock.stop();

        }

        return result;

    }

    public boolean isWon() {

        return this.won;

    }

    public int revealSecretNumber() {

        if (!finished) throw new IllegalStateException("Cannot reveal the secret number while the round is still in progress.");

        return secretNumber.getValue();

    }

    public Duration getElapsedTime() {

        if (!finished) throw new IllegalStateException("Cannot get elapsed time while the round is still in progress.");

        return this.elapsedTime;

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
