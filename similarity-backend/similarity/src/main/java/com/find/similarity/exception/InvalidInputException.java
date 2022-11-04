package com.find.similarity.exception;

import static com.find.similarity.constants.Constants.INVALID_INPUTS;

public class InvalidInputException extends Exception{
    public InvalidInputException(String message) {
        super(INVALID_INPUTS + " - " + message);
    }
}
