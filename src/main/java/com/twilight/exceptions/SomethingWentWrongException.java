package com.twilight.exceptions;

import lombok.Getter;

@Getter
public class SomethingWentWrongException extends RuntimeException {
    private final String error;
    public SomethingWentWrongException(String message, String error) {
        super(message);
        this.error = error;
    }
}
