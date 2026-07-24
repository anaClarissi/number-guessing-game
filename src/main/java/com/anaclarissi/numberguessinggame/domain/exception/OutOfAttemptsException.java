package com.anaclarissi.numberguessinggame.domain.exception;

public class OutOfAttemptsException extends RuntimeException {
    public OutOfAttemptsException(String message) {
        super(message);
    }
}
