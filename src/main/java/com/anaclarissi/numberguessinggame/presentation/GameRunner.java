package com.anaclarissi.numberguessinggame.presentation;

import com.anaclarissi.numberguessinggame.application.GameService;
import com.anaclarissi.numberguessinggame.domain.model.Difficulty;
import com.anaclarissi.numberguessinggame.domain.model.GameRound;
import com.anaclarissi.numberguessinggame.domain.model.GuessResult;

public class GameRunner {

    private final GameService gameService;

    private final ConsoleView view;

    private final ConsoleInputReader input;

    public GameRunner(GameService gameService, ConsoleView view, ConsoleInputReader input) {
        this.gameService = gameService;
        this.view = view;
        this.input = input;
    }

    public void run() {

        view.showWelcome();

        boolean playAgain = true;

        while (playAgain) {

            view.showDifficultyMenu();

            Difficulty difficulty = input.readDifficultyChoice();

            GameRound round = gameService.startNewRound(difficulty);

            while (!round.isOver()) {

                int guessValue = input.readGuess();

                GuessResult result = gameService.processGuess(round, guessValue);

                if (result == GuessResult.CORRECT) {

                    view.showVictory(round.getAttemptsUsed());

                } else if (round.isOver()) {

                    view.showDefeat(round.revealSecretNumber());

                } else {

                    view.showResult(result, guessValue);

                }

            }

            playAgain = input.readYesNo("Do you want to play again? (y/n)");

        }

        
    }

}
