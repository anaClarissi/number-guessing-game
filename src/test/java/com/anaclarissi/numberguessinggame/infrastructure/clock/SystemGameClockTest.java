package com.anaclarissi.numberguessinggame.infrastructure.clock;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SystemGameClockTest {

    @Test
    void shouldReturnDurationGreaterThanOrEqualToElapsedTime() throws InterruptedException {

        SystemGameClock clock = new SystemGameClock();

        clock.start();

        Thread.sleep(100);

        Duration duration = clock.stop();

        assertTrue(duration.toMillis() >= 100);

    }

}
