package com.anaclarissi.numberguessinggame.domain.exception;

public class InvalidGuessException extends RuntimeException {
    public InvalidGuessException(String message) {
        super(message);
    }
}
