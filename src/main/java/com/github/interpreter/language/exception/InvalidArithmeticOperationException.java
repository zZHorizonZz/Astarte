package com.github.interpreter.language.exception;

public class InvalidArithmeticOperationException extends RuntimeException {

    public InvalidArithmeticOperationException() {
    }

    public InvalidArithmeticOperationException(String message) {
        super(message);
    }

    public InvalidArithmeticOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
