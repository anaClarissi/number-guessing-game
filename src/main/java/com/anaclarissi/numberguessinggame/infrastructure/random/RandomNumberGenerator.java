package com.anaclarissi.numberguessinggame.infrastructure.random;

import com.anaclarissi.numberguessinggame.domain.service.NumberGeneratorPort;

import java.util.Random;

public class RandomNumberGenerator implements NumberGeneratorPort {

    private final Random random;

    public RandomNumberGenerator() {

        this.random = new Random();

    }

    @Override
    public int generate(int min, int max) {

        return random.nextInt(min, max + 1);

    }

}
