package com.lorenz.dental_scheduling.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
