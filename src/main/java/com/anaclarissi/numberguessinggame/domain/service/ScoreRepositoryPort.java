package com.anaclarissi.numberguessinggame.domain.service;

import com.anaclarissi.numberguessinggame.domain.model.Difficulty;
import com.anaclarissi.numberguessinggame.domain.model.ScoreRecord;

import java.util.Optional;

public interface ScoreRepositoryPort {

    void save(ScoreRecord record);

    Optional<ScoreRecord> findBestByDifficulty(Difficulty difficulty);

}
