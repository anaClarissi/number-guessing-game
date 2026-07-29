package com.anaclarissi.numberguessinggame.infrastructure.clock;

import com.anaclarissi.numberguessinggame.domain.service.GameClockPort;

import java.time.Duration;
import java.time.Instant;

public class SystemGameClock implements GameClockPort {

    private Instant startTime;

    @Override
    public void start() {
        this.startTime = Instant.now();
    }

    @Override
    public Duration stop() {

        Instant endTime = Instant.now();

        return Duration.between(startTime, endTime);

    }

}
