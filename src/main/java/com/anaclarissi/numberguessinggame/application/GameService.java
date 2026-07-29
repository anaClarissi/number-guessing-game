package com.anaclarissi.numberguessinggame.application;

import com.anaclarissi.numberguessinggame.domain.model.Difficulty;
import com.anaclarissi.numberguessinggame.domain.model.GameRound;
import com.anaclarissi.numberguessinggame.domain.model.GuessResult;
import com.anaclarissi.numberguessinggame.domain.model.SecretNumber;
import com.anaclarissi.numberguessinggame.domain.service.GameClockPort;
import com.anaclarissi.numberguessinggame.domain.service.NumberGeneratorPort;

public class GameService {

    private final NumberGeneratorPort numberGenerator;
    private final GameClockPort clock;

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 100;

    public GameService(NumberGeneratorPort numberGenerator, GameClockPort clock) {

        this.numberGenerator = numberGenerator;
        this.clock = clock;

    }

    public GameRound startNewRound(Difficulty difficulty) {

        int random = numberGenerator.generate(MIN_VALUE, MAX_VALUE);

        SecretNumber secretNumber = new SecretNumber(random);

        return new GameRound(secretNumber, difficulty, clock);

    }

    public GuessResult processGuess(GameRound round, int value) {

        return round.registerGuess(value);

    }

}
