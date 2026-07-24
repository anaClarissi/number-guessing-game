package com.anaclarissi.numberguessinggame.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SecretNumberTest {

    @Test
    void shouldCreateAValidNumberAndReturnIt() {

        SecretNumber secretNumber = new SecretNumber(50);

        assertEquals(50, secretNumber.getValue());

    }

    @Test
    void shouldThrowAnIllegalArgumentExceptionWhenTheValueIsLessThanOne() {

        assertThrows(IllegalArgumentException.class, () -> new SecretNumber(0));

    }

    @Test
    void shouldThrowAnIllegalArgumentExceptionWhenTheValueIsMoreThanOneHundred() {

        assertThrows(IllegalArgumentException.class, () -> new SecretNumber(101));

    }

    @Test
    void shouldNotThrowAnExceptionWhenTheValueIsOne() {

        assertDoesNotThrow(() -> new SecretNumber(1));

    }

    @Test
    void shouldNotThrowAnExceptionWhenTheValueIsOneHundred() {

        assertDoesNotThrow(() -> new SecretNumber(100));

    }

    @Test
    void shouldReturnTrueWhenTheGuessIsEqualToTheValue() {

        SecretNumber secretNumber = new SecretNumber(50);

        assertTrue(secretNumber.isEqualTo(50));

    }

    @Test
    void shouldReturnFalseWhenTheGuessIsNotEqualToValue() {

        SecretNumber secretNumber = new SecretNumber(50);

        assertFalse(secretNumber.isEqualTo(10));

    }

}
