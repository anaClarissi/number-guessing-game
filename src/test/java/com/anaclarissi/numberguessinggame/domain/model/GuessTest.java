package com.anaclarissi.numberguessinggame.domain.model;

import com.anaclarissi.numberguessinggame.domain.exception.InvalidGuessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GuessTest {

    @Test
    void shouldReturnValidValues() {

        int value = 50;
        int attemptValue = 10;

        Guess guess = new Guess(value, attemptValue);

        assertEquals(value, guess.getValue());
        assertEquals(attemptValue, guess.getAttemptNumber());

    }

    @Test
    void shouldThrowAnInvalidGuessExceptionWhenTheValueIsLessThanOne() {

        assertThrows(InvalidGuessException.class, () -> new Guess(0, 10));

    }

    @Test
    void shouldThrowAnInvalidGuessExceptionWhenTheValueIsGreaterThanOneHundred() {

        assertThrows(InvalidGuessException.class, () -> new Guess(101, 10));

    }

    @Test
    void shouldThrowAnInvalidGuessExceptionWhenTheAttemptValueIsLessThanOne() {

        assertThrows(InvalidGuessException.class, () -> new Guess(50, 0));

    }

    @Test
    void shouldNotThrowAnExceptionWhenTheValueIsOne() {

        assertDoesNotThrow(() -> new Guess(1, 10));

    }

    @Test
    void shouldNotThrowAnExceptionWhenTheValueIsOneHundred() {

        assertDoesNotThrow(() -> new Guess(100, 10));

    }

    @Test
    void shouldNotThrowAnExceptionWhenTheAttemptValueIsOne() {

        assertDoesNotThrow(() -> new Guess(50, 1));

    }


}
