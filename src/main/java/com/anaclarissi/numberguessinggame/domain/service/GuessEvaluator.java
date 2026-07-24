package com.anaclarissi.numberguessinggame.domain.service;

import com.anaclarissi.numberguessinggame.domain.model.Guess;
import com.anaclarissi.numberguessinggame.domain.model.GuessResult;
import com.anaclarissi.numberguessinggame.domain.model.SecretNumber;

public class GuessEvaluator {

    public GuessResult evaluate (SecretNumber secretNumber, Guess guess) {

        if (guess.getValue() > secretNumber.getValue()) return GuessResult.TOO_HIGH;

        if (guess.getValue() < secretNumber.getValue()) return GuessResult.TOO_LOW;

        return GuessResult.CORRECT;

    }

}
