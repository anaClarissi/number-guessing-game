package com.anaclarissi.numberguessinggame.infrastructure.random;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomNumberGeneratorTest {

    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    @Test
    void shouldAlwaysGenerateNumberWithinRange() {

        for (int i = 0; i < 1000; i++) {

            int result = randomNumberGenerator.generate(1, 100);

            assertTrue(result >= 1 && result <= 100);

        }

    }

    @Test
    void shouldAlwaysGenerateNumberBetweenOneAndFifty() {

        for (int i = 0; i < 100; i++) {

            int result = randomNumberGenerator.generate(1, 50);

            assertTrue(result >= 1 && result <= 50);

        }

    }

    @Test
    void shouldAlwaysGenerateNumberBetweenFiftyAndOneHundred() {

        for (int i = 0; i < 100; i++) {

            int result = randomNumberGenerator.generate(50, 100);

            assertTrue(result >= 50 && result <= 100);

        }

    }

}
