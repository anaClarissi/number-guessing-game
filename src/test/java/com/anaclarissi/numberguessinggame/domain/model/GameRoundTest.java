package com.anaclarissi.numberguessinggame.domain.model;

import com.anaclarissi.numberguessinggame.domain.exception.OutOfAttemptsException;
import com.anaclarissi.numberguessinggame.domain.service.GameClockPort;
import com.anaclarissi.numberguessinggame.infrastructure.clock.SystemGameClock;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameRoundTest {

    private final GameClockPort clock = new SystemGameClock();

    @Test
    void shouldBeAWinOnTheFirstAttempt() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.EASY, clock);

        GuessResult result = gameRound.registerGuess(50);

        assertTrue(gameRound.isWon());
        assertTrue(gameRound.isOver());

        assertEquals(1, gameRound.getAttemptsUsed());
        assertEquals(GuessResult.CORRECT, result);

    }

    @Test
    void shouldReturnTooLowValueAndIsOverMustBeFalse() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.EASY, clock);

        GuessResult result = gameRound.registerGuess(30);

        assertEquals(GuessResult.TOO_LOW, result);

        assertFalse(gameRound.isOver());

    }

    @Test
    void shouldReturnTooHighValueAndIsOverMustBeFalse() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.EASY, clock);

        GuessResult result = gameRound.registerGuess(100);

        assertEquals(GuessResult.TOO_HIGH, result);

        assertFalse(gameRound.isOver());

    }

    @Test
    void shouldLoseWhenAllAttemptsAreUsedWithoutCorrectGuess() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.HARD, clock);

        gameRound.registerGuess(10);
        gameRound.registerGuess(20);
        gameRound.registerGuess(30);

        assertTrue(gameRound.isOver());

        assertFalse(gameRound.isWon());

    }

    @Test
    void shouldThrowAnOutOfAttemptsExceptionAfterTheGameFinished() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.EASY, clock);

        gameRound.registerGuess(50);

        assertThrows(OutOfAttemptsException.class, () -> gameRound.registerGuess(10));

    }

    @Test
    void shouldReturnTheCorrectNumberOfAttempts() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.EASY, clock);

        gameRound.registerGuess(10);
        gameRound.registerGuess(20);

        assertEquals(2, gameRound.getAttemptsUsed());

    }

    @Test
    void shouldReturnTheCorrectSecretNumberAfterRoundFinished() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.EASY, clock);

        gameRound.registerGuess(50);

        assertEquals(50, gameRound.revealSecretNumber());

    }

    @Test
    void shouldThrowAnIllegalStateExceptionIfCallingRevealSecretNumberBeforeRoundFinished() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.EASY, clock);

        gameRound.registerGuess(10);

        assertThrows(IllegalStateException.class, () -> gameRound.revealSecretNumber());

    }

    @Test
    void shouldThrowAnIllegalStateExceptionIfCallingGetElapsedTimeBeforeRoundFinished() {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.EASY, clock);

        gameRound.registerGuess(10);

        assertThrows(IllegalStateException.class, () -> gameRound.getElapsedTime());

    }

    @Test
    void shouldReturnTheCorrectElapsedTime() throws InterruptedException {

        GameRound gameRound = new GameRound(new SecretNumber(50), Difficulty.HARD, clock);

        for (int i = 1; i <= 3; i++) {

            gameRound.registerGuess(i);

            Thread.sleep(50);

        }

        assertTrue(gameRound.getElapsedTime().toMillis() >= 100);

    }

}
