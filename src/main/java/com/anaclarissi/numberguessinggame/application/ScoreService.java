package com.anaclarissi.numberguessinggame.application;

import com.anaclarissi.numberguessinggame.domain.model.Difficulty;
import com.anaclarissi.numberguessinggame.domain.model.ScoreRecord;
import com.anaclarissi.numberguessinggame.domain.service.ScoreRepositoryPort;

import java.util.Optional;

public class ScoreService {

    private final ScoreRepositoryPort repository;

    public ScoreService(ScoreRepositoryPort repository) {
        this.repository = repository;
    }

    public void registerResult(Difficulty difficulty, int attempts) {

        ScoreRecord scoreRecord = new ScoreRecord(difficulty, attempts);

        repository.save(scoreRecord);

    }

    public Optional<ScoreRecord> getHighScore(Difficulty difficulty) {

        return repository.findBestByDifficulty(difficulty);

    }

}
