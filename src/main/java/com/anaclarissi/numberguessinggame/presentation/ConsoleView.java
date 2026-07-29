package com.anaclarissi.numberguessinggame.presentation;

import com.anaclarissi.numberguessinggame.domain.model.GuessResult;

import java.time.Duration;

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
        System.out.print("Your choice: ");

    }

    public void showResult(GuessResult result, int guessValue) {

        if (result == GuessResult.TOO_LOW) {

            System.out.printf("Incorrect! The number is greater than %d.%n", guessValue);

        } else {

            System.out.printf("Incorrect! The number is less than %d.%n", guessValue);

        }

    }

    public void showVictory(int attempts, Duration elapsedTime) {

        String timer = getTimer(elapsedTime);

        System.out.printf("Congratulations! You guessed the correct number in %d attempt(s), your duration: %s%n", attempts, timer);

    }

    public void showDefeat(int secretValue, Duration elapsedTime) {

        String timer = getTimer(elapsedTime);

        System.out.printf("You ran out of attempts! The correct number was %d, your duration: %s%n", secretValue, timer);

    }

    public void showGuessPrompt() {

        System.out.print("Enter a value: ");

    }

    private String getTimer(Duration elapsedTime) {

        int minutes = elapsedTime.toMinutesPart();

        int seconds = elapsedTime.toSecondsPart();

        return String.format("%02d:%02d", minutes, seconds);

    }

}
