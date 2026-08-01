package com.anaclarissi.numberguessinggame.infrastructure.persistence;

import com.anaclarissi.numberguessinggame.domain.model.Difficulty;
import com.anaclarissi.numberguessinggame.domain.model.ScoreRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryScoreRepositoryTest {

    private InMemoryScoreRepository scoreRepository;

    @BeforeEach
    void setUp() {
        scoreRepository = new InMemoryScoreRepository();
    }

    @Test
    void shouldSaveTheFirstRecordForTheDifficultyEasy() {

        scoreRepository.save(new ScoreRecord(Difficulty.EASY, 5));

        assertFalse(scoreRepository.findBestByDifficulty(Difficulty.EASY).isEmpty());

    }

    @Test
    void shouldSaveTheFirstRecordForTheDifficultyMedium() {

        scoreRepository.save(new ScoreRecord(Difficulty.MEDIUM, 3));

        assertFalse(scoreRepository.findBestByDifficulty(Difficulty.MEDIUM).isEmpty());

    }

    @Test
    void shouldSaveTheFirstRecordForTheDifficultyHard() {

        scoreRepository.save(new ScoreRecord(Difficulty.HARD, 1));

        assertFalse(scoreRepository.findBestByDifficulty(Difficulty.HARD).isEmpty());

    }

    @Test
    void shouldReturnEmptyWhenCallingFindBestByDifficultyEasyWithoutSavedRecord() {

        Optional<ScoreRecord> best = scoreRepository.findBestByDifficulty(Difficulty.EASY);

        assertTrue(best.isEmpty());

    }

    @Test
    void shouldReturnEmptyWhenCallingFindBestByDifficultyMediumWithoutSavedRecord() {

        Optional<ScoreRecord> best = scoreRepository.findBestByDifficulty(Difficulty.MEDIUM);

        assertTrue(best.isEmpty());

    }

    @Test
    void shouldReturnEmptyWhenCallingFindBestByDifficultyHardWithoutSavedRecord() {

        Optional<ScoreRecord> best = scoreRepository.findBestByDifficulty(Difficulty.HARD);

        assertTrue(best.isEmpty());

    }

    @Test
    void shouldPrevailTheBestEasyRecord() {

        scoreRepository.save(new ScoreRecord(Difficulty.EASY, 5));

        scoreRepository.save(new ScoreRecord(Difficulty.EASY, 10));

        assertEquals(5, scoreRepository.findBestByDifficulty(Difficulty.EASY).get().getBestAttempts());

    }

    @Test
    void shouldPrevailTheBestMediumRecord() {

        scoreRepository.save(new ScoreRecord(Difficulty.MEDIUM, 3));

        scoreRepository.save(new ScoreRecord(Difficulty.MEDIUM, 5));

        assertEquals(3, scoreRepository.findBestByDifficulty(Difficulty.MEDIUM).get().getBestAttempts());

    }

    @Test
    void shouldPrevailTheBestHardRecord() {

        scoreRepository.save(new ScoreRecord(Difficulty.HARD, 1));

        scoreRepository.save(new ScoreRecord(Difficulty.HARD, 3));

        assertEquals(1, scoreRepository.findBestByDifficulty(Difficulty.HARD).get().getBestAttempts());

    }

    @Test
    void shouldPrevailTheBestEasyRecordAfterAWorseRecord() {

        scoreRepository.save(new ScoreRecord(Difficulty.EASY, 10));

        scoreRepository.save(new ScoreRecord(Difficulty.EASY, 5));

        assertEquals(5, scoreRepository.findBestByDifficulty(Difficulty.EASY).get().getBestAttempts());

    }

    @Test
    void shouldPrevailTheBestMediumRecordAfterAWorseRecord() {

        scoreRepository.save(new ScoreRecord(Difficulty.MEDIUM, 5));

        scoreRepository.save(new ScoreRecord(Difficulty.MEDIUM, 3));

        assertEquals(3, scoreRepository.findBestByDifficulty(Difficulty.MEDIUM).get().getBestAttempts());

    }

    @Test
    void shouldPrevailTheBestHardRecordAfterAWorseRecord() {

        scoreRepository.save(new ScoreRecord(Difficulty.HARD, 3));

        scoreRepository.save(new ScoreRecord(Difficulty.HARD, 1));

        assertEquals(1, scoreRepository.findBestByDifficulty(Difficulty.HARD).get().getBestAttempts());

    }


}
