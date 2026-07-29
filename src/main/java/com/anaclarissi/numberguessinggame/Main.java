package com.anaclarissi.numberguessinggame;

import com.anaclarissi.numberguessinggame.application.GameService;
import com.anaclarissi.numberguessinggame.domain.service.GameClockPort;
import com.anaclarissi.numberguessinggame.domain.service.NumberGeneratorPort;
import com.anaclarissi.numberguessinggame.infrastructure.clock.SystemGameClock;
import com.anaclarissi.numberguessinggame.infrastructure.random.RandomNumberGenerator;
import com.anaclarissi.numberguessinggame.presentation.ConsoleInputReader;
import com.anaclarissi.numberguessinggame.presentation.ConsoleView;
import com.anaclarissi.numberguessinggame.presentation.GameRunner;

public class Main {

    public static void main(String[] args) {

        NumberGeneratorPort generator = new RandomNumberGenerator();

        GameClockPort clock = new SystemGameClock();

        GameService gameService = new GameService(generator, clock);

        ConsoleView view = new ConsoleView();

        ConsoleInputReader input = new ConsoleInputReader();

        GameRunner runner = new GameRunner(gameService, view, input);

        runner.run();

    }

}
