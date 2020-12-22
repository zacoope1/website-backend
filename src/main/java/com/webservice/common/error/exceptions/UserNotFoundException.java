package com.webservice.common.error.exceptions;

import com.webservice.common.Constants;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super(Constants.USER_NOT_FOUND_MESSAGE);
    }
}
