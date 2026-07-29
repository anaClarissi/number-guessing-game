package com.anaclarissi.numberguessinggame.application;

import com.anaclarissi.numberguessinggame.domain.model.Difficulty;
import com.anaclarissi.numberguessinggame.domain.model.GameRound;
import com.anaclarissi.numberguessinggame.domain.model.GuessResult;
import com.anaclarissi.numberguessinggame.domain.service.GameClockPort;
import com.anaclarissi.numberguessinggame.domain.service.NumberGeneratorPort;

import com.anaclarissi.numberguessinggame.infrastructure.clock.SystemGameClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {

    @Mock
    private NumberGeneratorPort numberGenerator;

    private final GameClockPort clock = new SystemGameClock();

    private static final int MIN = 1;
    private static final int MAX = 100;

    private GameService gameService;

    @BeforeEach
    void setUp() {
        gameService = new GameService(numberGenerator, clock);
    }

    @Test
    void shouldCreateGameRoundWithSecretNumberFromGenerator() {

        when(numberGenerator.generate(MIN, MAX)).thenReturn(42);

        GameRound gameRound = gameService.startNewRound(Difficulty.EASY);

        GuessResult guessResult = gameService.processGuess(gameRound, 42);

        assertEquals(GuessResult.CORRECT, guessResult);

    }

    @Test
    void shouldReturnATooLowGuessResultWhenCallingGameServiceProcessGuess() {

        when(numberGenerator.generate(MIN, MAX)).thenReturn(50);

        GameRound gameRound = gameService.startNewRound(Difficulty.EASY);

        GuessResult guessResult = gameService.processGuess(gameRound, 30);

        assertEquals(GuessResult.TOO_LOW, guessResult);

    }

    @Test
    void shouldReturnATooHighGuessResultWhenCallingGameServiceProcessGuess() {

        when(numberGenerator.generate(MIN, MAX)).thenReturn(50);

        GameRound gameRound = gameService.startNewRound(Difficulty.EASY);

        GuessResult guessResult = gameService.processGuess(gameRound, 60);

        assertEquals(GuessResult.TOO_HIGH, guessResult);

    }

}
