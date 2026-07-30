package com.anaclarissi.numberguessinggame.application;

import com.anaclarissi.numberguessinggame.domain.model.Guess;
import com.anaclarissi.numberguessinggame.domain.model.SecretNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HintServiceTest {

    private final HintService hintService = new HintService();

    private static final SecretNumber SECRET_NUMBER = new SecretNumber(70);
    private static final int ATTEMPT_GUESS = 1;

    @Test
    void shouldReturnTheCorrectHintWhenTheDistanceBetweenValuesIsClose() {

        String hint = hintService.giveHint(SECRET_NUMBER, new Guess(65, ATTEMPT_GUESS));

        assertEquals("You are very close!", hint);

    }

    @Test
    void shouldReturnTheCorrectHintWhenTheDistanceBetweenValuesIsFar() {

        String hint = hintService.giveHint(SECRET_NUMBER, new Guess(10, ATTEMPT_GUESS));

        assertEquals("You are very far!", hint);

    }

    @Test
    void shouldReturnTheCorrectHintWhenTheSecretNumberIsEven() {

        String hint = hintService.giveHint(SECRET_NUMBER, new Guess(60, ATTEMPT_GUESS));

        assertEquals("The number is even.", hint);

    }

    @Test
    void shouldReturnTheCorrectHintWhenTheSecretNumberIsOdd() {

        String hint = hintService.giveHint(new SecretNumber(75), new Guess(65, ATTEMPT_GUESS));

        assertEquals("The number is odd.", hint);

    }

}
