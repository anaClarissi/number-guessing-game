package com.anaclarissi.numberguessinggame.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecretNumberTest {

    @Test
    public void shouldCreateAValidNumberAndReturnIt() {

        SecretNumber secretNumber = new SecretNumber(50);

        assertEquals(50, secretNumber.getValue());

    }

    @Test
    public void shouldThrowAnIllegalArgumentExceptionWhenTheValueIsLessThanOne() {

        assertThrows(IllegalArgumentException.class, () -> new SecretNumber(0));

    }

    @Test
    public void shouldThrowAnIllegalArgumentExceptionWhenTheValueIsMoreThanOneHundred() {

        assertThrows(IllegalArgumentException.class, () -> new SecretNumber(101));

    }

    @Test
    public void shouldNotThrowAnExceptionWhenTheValueIsOne() {

        assertDoesNotThrow(() -> new SecretNumber(1));

    }

    @Test
    public void shouldNotThrowAnExceptionWhenTheValueIsOneHundred() {

        assertDoesNotThrow(() -> new SecretNumber(100));

    }

    @Test
    public void shouldReturnTrueWhenTheGuessIsEqualToTheValue() {

        SecretNumber secretNumber = new SecretNumber(50);

        assertTrue(secretNumber.isEqualTo(50));

    }

    @Test
    public void shouldReturnFalseWhenTheGuessIsNotEqualToValue() {

        SecretNumber secretNumber = new SecretNumber(50);

        assertFalse(secretNumber.isEqualTo(10));

    }

}
