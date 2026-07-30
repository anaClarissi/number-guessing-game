package com.anaclarissi.numberguessinggame.application;

import com.anaclarissi.numberguessinggame.domain.model.Guess;
import com.anaclarissi.numberguessinggame.domain.model.SecretNumber;

public class HintService {

    public String giveHint(SecretNumber secretNumber, Guess lastGuess) {

        int distance = Math.abs(secretNumber.getValue() - lastGuess.getValue());

        if (distance <= 5) return "You are very close!";

        if (distance >= 20) return "You are very far!";

        if (secretNumber.getValue() % 2 == 0) return "The number is even.";

        return "The number is odd.";

    }

}
