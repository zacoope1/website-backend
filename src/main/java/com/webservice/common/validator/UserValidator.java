package com.webservice.common.validator;

import com.webservice.common.error.exceptions.UserValidationException;
import com.webservice.user.model.requests.CreateUserRequest;
import com.webservice.user.model.requests.UserInfoRequest;


public class UserValidator {

    public void validateGetUserInfoRequest(UserInfoRequest request) throws UserValidationException {
        if ((request.getUsername().isEmpty()) || (request.getUsername() == null)) {
            throw new UserValidationException();
        }
        if ((request.getPassword().isEmpty()) || (request.getPassword() == null)) {
            throw new UserValidationException();
        }
    }

    public void validateCreateUserRequest(CreateUserRequest request) throws UserValidationException {
        if ((request.getUsername().isEmpty()) || (request.getUsername() == null)) {
            throw new UserValidationException();
        }

        if ((request.getFirstName().isEmpty()) || (request.getFirstName() == null)) {
            throw new UserValidationException();
        }

        if ((request.getLastName().isEmpty()) || (request.getLastName() == null)) {
            throw new UserValidationException();
        }

        if ((request.getPassword().isEmpty()) || (request.getPassword() == null)) {
            throw new UserValidationException();
        }
    }

}
