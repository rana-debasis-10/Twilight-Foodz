package com.twilight.exceptions;

public class GeocodingError extends RuntimeException {
    public GeocodingError(String message) {
        super(message);
    }
}
