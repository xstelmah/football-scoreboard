package com.stelmah.sportsdata.exception;

/**
 * Custom exception class for handling specific scoreboard errors.
 */
public class ScoreBoardException extends RuntimeException {

    public ScoreBoardException(String message) {
        super(message);
    }
}
