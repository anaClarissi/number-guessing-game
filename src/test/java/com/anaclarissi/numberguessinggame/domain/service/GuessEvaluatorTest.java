package com.anaclarissi.numberguessinggame.domain.service;

import com.anaclarissi.numberguessinggame.domain.model.Guess;
import com.anaclarissi.numberguessinggame.domain.model.GuessResult;
import com.anaclarissi.numberguessinggame.domain.model.SecretNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessEvaluatorTest {

    private final GuessEvaluator guessEvaluator = new GuessEvaluator();

    @Test
    void shouldReturnAGuessResultTooHighWhenGuessIsGreaterThanSecretNumber() {

        Guess guess = new Guess(50, 10);

        SecretNumber secretNumber = new SecretNumber(10);

        assertEquals(GuessResult.TOO_HIGH, guessEvaluator.evaluate(secretNumber, guess));

    }

    @Test
    void shouldReturnAGuessResultTooLowWhenGuessIsLessThanSecretNumber() {

        Guess guess = new Guess(10, 10);

        SecretNumber secretNumber = new SecretNumber(50);

        assertEquals(GuessResult.TOO_LOW, guessEvaluator.evaluate(secretNumber, guess));

    }

    @Test
    void shouldReturnAGuessResultCorrectWhenGuessIsEqualToSecretNumber() {

        Guess guess = new Guess(50, 10);

        SecretNumber secretNumber = new SecretNumber(50);

        assertEquals(GuessResult.CORRECT, guessEvaluator.evaluate(secretNumber, guess));

    }

}
