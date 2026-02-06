package com.twilight.exceptions;

public class LimitExceeded extends RuntimeException {
    public LimitExceeded(int message) {
        super("Limit Exceeded : "+message);
    }
}
