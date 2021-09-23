package com.github.interpreter.validation.syntax.exception;

public class UnknownSyntaxException extends RuntimeException {

    public UnknownSyntaxException() {
    }

    public UnknownSyntaxException(String message) {
        super(message);
    }

    public UnknownSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }
}
