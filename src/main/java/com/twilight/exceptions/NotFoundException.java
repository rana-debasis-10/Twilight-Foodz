package com.twilight.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String error;
    public NotFoundException(String message, String error) {
        super(message);
        this.error = error;
    }
}
