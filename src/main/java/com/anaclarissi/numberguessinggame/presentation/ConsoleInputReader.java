package com.anaclarissi.numberguessinggame.presentation;

import com.anaclarissi.numberguessinggame.domain.model.Difficulty;

import java.util.Scanner;

public class ConsoleInputReader {

    private final Scanner scanner = new Scanner(System.in);

    public Difficulty readDifficultyChoice() {

        int difficultyIntValue = scanner.nextInt();

        scanner.nextLine();

        if (difficultyIntValue < 1 || difficultyIntValue > 3) {

            throw new IllegalArgumentException("The choice should be between 1 and 3!");

        }

        return switch (difficultyIntValue) {

            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.MEDIUM;
            case 3 -> Difficulty.HARD;
            default -> throw new IllegalArgumentException("Unexpected value: " + difficultyIntValue);

        };

    }

    public int readGuess() {

        int guessValue = scanner.nextInt();

        scanner.nextLine();

        if (guessValue < 1 || guessValue > 100) throw new IllegalArgumentException("The Guess value should be between 1 and 100!");

        return guessValue;

    }

    public boolean readYesNo(String question) {

        System.out.print(question);

        char answer = scanner.nextLine().toLowerCase().charAt(0);

        return answer == 'y';

    }

}
