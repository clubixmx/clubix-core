package com.clubix.usecase.shared;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}

