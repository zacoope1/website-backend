package com.webservice.common.error.exceptions;

import com.webservice.common.Constants;

public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException() {
            super(Constants.PASSWORD_MISMATCH_MESSAGE);
        }
}
