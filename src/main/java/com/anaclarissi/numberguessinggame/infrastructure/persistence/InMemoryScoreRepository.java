package com.anaclarissi.numberguessinggame.infrastructure.persistence;

import com.anaclarissi.numberguessinggame.domain.model.Difficulty;
import com.anaclarissi.numberguessinggame.domain.model.ScoreRecord;
import com.anaclarissi.numberguessinggame.domain.service.ScoreRepositoryPort;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryScoreRepository implements ScoreRepositoryPort {

    private final Map<Difficulty, ScoreRecord> scores = new HashMap<>();

    @Override
    public void save(ScoreRecord record) {

        Optional<ScoreRecord> current = findBestByDifficulty(record.getDifficulty());

        if (current.isEmpty() || record.getBestAttempts() < current.get().getBestAttempts()) {

            scores.put(record.getDifficulty(), record);

        }

    }

    @Override
    public Optional<ScoreRecord> findBestByDifficulty(Difficulty difficulty) {
        return Optional.ofNullable(scores.get(difficulty));
    }

}
