package com.twilight.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) {
        super("User Already Exists for : "+message);
    }
}
