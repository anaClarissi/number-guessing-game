package com.anaclarissi.numberguessinggame.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ScoreRecordTest {

    @Test
    void shouldReturnEasyDifficultyValueWhenCallingGetDifficulty() {

        ScoreRecord scoreRecord = new ScoreRecord(Difficulty.EASY, 10);

        assertEquals(Difficulty.EASY, scoreRecord.getDifficulty());

    }

    @Test
    void shouldReturnMediumDifficultyValueWhenCallingGetDifficulty() {

        ScoreRecord scoreRecord = new ScoreRecord(Difficulty.MEDIUM, 5);

        assertEquals(Difficulty.MEDIUM, scoreRecord.getDifficulty());

    }

    @Test
    void shouldReturnHardDifficultyValueWhenCallingGetDifficulty() {

        ScoreRecord scoreRecord = new ScoreRecord(Difficulty.HARD, 3);

        assertEquals(Difficulty.HARD, scoreRecord.getDifficulty());

    }

    @Test
    void shouldThrowAnIllegalArgumentExceptionIfDifficultyIsNull() {

        assertThrows(IllegalArgumentException.class, () -> new ScoreRecord(null, 10));

    }

    @Test
    void shouldThrowAnIllegalArgumentExceptionIfBestAttemptsIsLessThanTheMinValue() {

        assertThrows(IllegalArgumentException.class, () -> new ScoreRecord(Difficulty.EASY, 0));

    }

    @Test
    void shouldThrowAnIllegalArgumentExceptionIfBestAttemptsIsGreaterThanTheMaxValue() {

        assertThrows(IllegalArgumentException.class, () -> new ScoreRecord(Difficulty.EASY, 11));

    }

    @Test
    void shouldNotThrowAnIllegalArgumentExceptionIfBestAttemptsIsEqualToTheMinValue() {

        assertDoesNotThrow(() -> new ScoreRecord(Difficulty.EASY, 1));

    }

    @Test
    void shouldNotThrowAnIllegalArgumentExceptionIfBestAttemptsIsEqualToTheMaxValue() {

        assertDoesNotThrow(() -> new ScoreRecord(Difficulty.EASY, 10));

    }

}
