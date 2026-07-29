package com.anaclarissi.numberguessinggame.domain.service;

import java.time.Duration;

public interface GameClockPort {

    void start();

    Duration stop();

}
