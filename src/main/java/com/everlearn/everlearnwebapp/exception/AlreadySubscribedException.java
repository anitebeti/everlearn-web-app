package com.everlearn.everlearnwebapp.exception;

public class AlreadySubscribedException extends Exception{

    public AlreadySubscribedException() {
        super("User already subscribed to this course");
    }
}
