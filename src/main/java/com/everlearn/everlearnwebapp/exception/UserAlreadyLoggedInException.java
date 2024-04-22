package com.everlearn.everlearnwebapp.exception;

public class UserAlreadyLoggedInException extends Exception {

    public UserAlreadyLoggedInException() {
        super("User already logged in");
    }
}
