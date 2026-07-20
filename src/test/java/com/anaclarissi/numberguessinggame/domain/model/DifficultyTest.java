package com.anaclarissi.numberguessinggame.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifficultyTest {

    @Test
    void shouldReturnTheNumberTen () {

        Difficulty difficulty = Difficulty.EASY;

        assertEquals(10, difficulty.getMaxAttempts());

    }

    @Test
    void shouldReturnEasyAsString () {

        Difficulty difficulty = Difficulty.EASY;

        assertEquals("easy", difficulty.getLabel());

    }

    @Test
    void shouldReturnTheNumberFive () {

        Difficulty difficulty = Difficulty.MEDIUM;

        assertEquals(5, difficulty.getMaxAttempts());

    }

    @Test
    void shouldReturnMediumAsString () {

        Difficulty difficulty = Difficulty.MEDIUM;

        assertEquals("medium", difficulty.getLabel());

    }

    @Test
    void shouldReturnTheNumberThree () {

        Difficulty difficulty = Difficulty.HARD;

        assertEquals(3, difficulty.getMaxAttempts());

    }

    @Test
    void shouldReturnHardAsString () {

        Difficulty difficulty = Difficulty.HARD;

        assertEquals("hard", difficulty.getLabel());

    }

}
