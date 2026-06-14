package com.twilight.exceptions;

import lombok.Getter;

@Getter
public class UnAuthorizedException extends RuntimeException {
    private final String error;
    public UnAuthorizedException(String message, String error) {
        super(message);
        this.error = error;
    }
}
