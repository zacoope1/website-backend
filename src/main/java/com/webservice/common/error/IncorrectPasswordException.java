package com.webservice.common.error;

public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException(String message) {
            super(message);
        }
}
