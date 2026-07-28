package com.anaclarissi.numberguessinggame.presentation;

import com.anaclarissi.numberguessinggame.domain.model.GuessResult;

public class ConsoleView {

    public void showWelcome() {

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");

    }

    public void showDifficultyMenu() {

        System.out.println("Please select the difficulty level:");
        System.out.println("1. Easy (10 chances)");
        System.out.println("2. Medium (5 chances)");
        System.out.println("3. Hard (3 chances)");

    }

    public void showResult(GuessResult result, int guessValue) {

        if (result == GuessResult.TOO_LOW) {

            System.out.printf("Incorrect! The number is greater than %d.%n", guessValue);

        } else {

            System.out.printf("Incorrect! The number is less than %d.%n", guessValue);

        }

    }

    public void showVictory(int attempts) {

        System.out.printf("Congratulations! You guessed the correct number in %d attempt(s).%n", attempts);

    }

    public void showDefeat(int secretValue) {

        System.out.printf("You ran out of attempts! The correct number was %d.%n", secretValue);

    }

}
