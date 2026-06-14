package com.twilight.exceptions;

import lombok.Getter;

@Getter
public class InvalidFileException extends RuntimeException {
    private final String error;
    public InvalidFileException(String message, String error) {
        super(message);
        this.error = error;
    }
}
