package com.twilight.exceptions;

import lombok.Getter;

@Getter
public class GeocodingError extends RuntimeException {
    private final String error;
    public GeocodingError(String message, String error) {
        super(message);
        this.error = error;
    }
}
