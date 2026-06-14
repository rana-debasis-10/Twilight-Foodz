package com.twilight.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final String error;
    public BadRequestException(String message,String error) {
        super(message);
        this.error = error;
    }
}
