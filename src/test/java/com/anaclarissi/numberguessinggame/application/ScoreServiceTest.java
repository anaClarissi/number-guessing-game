package com.anaclarissi.numberguessinggame.application;

import com.anaclarissi.numberguessinggame.domain.model.Difficulty;
import com.anaclarissi.numberguessinggame.domain.service.ScoreRepositoryPort;
import com.anaclarissi.numberguessinggame.infrastructure.persistence.InMemoryScoreRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreServiceTest {

    private ScoreRepositoryPort repository;
    private ScoreService scoreService;

    @BeforeEach
    void setUp() {

        repository = new InMemoryScoreRepository();
        scoreService = new ScoreService(repository);

    }

    @Test
    void shouldReturnFiveAsBestEasyRecord() {

        scoreService.registerResult(Difficulty.EASY, 5);

        assertEquals(5, scoreService.getHighScore(Difficulty.EASY).get().getBestAttempts());

    }

    @Test
    void shouldReturnThreeAsBestMediumRecord() {

        scoreService.registerResult(Difficulty.MEDIUM, 3);

        assertEquals(3, scoreService.getHighScore(Difficulty.MEDIUM).get().getBestAttempts());

    }

    @Test
    void shouldReturnOneAsBestHardRecord() {

        scoreService.registerResult(Difficulty.HARD, 1);

        assertEquals(1, scoreService.getHighScore(Difficulty.HARD).get().getBestAttempts());

    }

    @Test
    void shouldReturnAnEmptyValueForADifficultyEasyNotRegistered() {

        assertTrue(scoreService.getHighScore(Difficulty.EASY).isEmpty());

    }

    @Test
    void shouldReturnAnEmptyValueForADifficultyMediumNotRegistered() {

        assertTrue(scoreService.getHighScore(Difficulty.MEDIUM).isEmpty());

    }

    @Test
    void shouldReturnAnEmptyValueForADifficultyHardNotRegistered() {

        assertTrue(scoreService.getHighScore(Difficulty.HARD).isEmpty());

    }

    @Test
    void shouldAlwaysReturnTheBestEasyResult() {

        scoreService.registerResult(Difficulty.EASY, 10);
        scoreService.registerResult(Difficulty.EASY, 5);

        assertEquals(5, scoreService.getHighScore(Difficulty.EASY).get().getBestAttempts());

    }

    @Test
    void shouldAlwaysReturnTheBestMediumResult() {

        scoreService.registerResult(Difficulty.MEDIUM, 5);
        scoreService.registerResult(Difficulty.MEDIUM, 3);

        assertEquals(3, scoreService.getHighScore(Difficulty.MEDIUM).get().getBestAttempts());

    }

    @Test
    void shouldAlwaysReturnTheBestHardResult() {

        scoreService.registerResult(Difficulty.HARD, 3);
        scoreService.registerResult(Difficulty.HARD, 1);

        assertEquals(1, scoreService.getHighScore(Difficulty.HARD).get().getBestAttempts());

    }


}
