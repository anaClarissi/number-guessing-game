package com.anaclarissi.numberguessinggame.domain.model;

import com.anaclarissi.numberguessinggame.domain.service.HintService;
import com.anaclarissi.numberguessinggame.domain.exception.OutOfAttemptsException;
import com.anaclarissi.numberguessinggame.domain.service.GameClockPort;
import com.anaclarissi.numberguessinggame.domain.service.GuessEvaluator;

import java.time.Duration;

public class GameRound {

    private final SecretNumber secretNumber;
    private final Difficulty difficulty;
    private final GuessEvaluator guessEvaluator;
    private final GameClockPort clock;
    private final HintService hintService;

    private Duration elapsedTime;
    private Guess lastGuess;

    private int attemptsUsed;
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
        hintService = new HintService();

        this.clock.start();

    }

    public GuessResult registerGuess(int value) {

        if (finished) throw new OutOfAttemptsException("The game round was already finished.");

        setAttemptsUsed(getAttemptsUsed() + 1);

        Guess guess = new Guess(value, getAttemptsUsed());

        this.lastGuess = guess;

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

    public String getHint() {

        if (getAttemptsUsed() < 2) throw new IllegalStateException("Hint is only available from the second attempt onwards.");

        return hintService.giveHint(secretNumber, lastGuess);

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
