package com.webservice.common.error.exceptions;

import com.webservice.common.Constants;

public class UserValidationException extends Exception {

    public UserValidationException() {
        super(Constants.BAD_REQUEST_MESSAGE);
    }

}
