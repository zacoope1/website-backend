package com.webservice.common.error.exceptions;

import com.webservice.common.Constants;

public class SessionNotFoundException extends Exception{
    public SessionNotFoundException() {
        super(Constants.SESSION_NOT_FOUND_MESSAGE);
    }
}
