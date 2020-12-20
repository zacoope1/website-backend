package com.webservice.common.validator;

import com.webservice.common.error.UserValidationException;
import com.webservice.user.model.requests.CreateUserRequest;


public class UserValidator {

    public void validateCreateUser(CreateUserRequest request) throws UserValidationException {

        if ((request.getUsername().isEmpty()) || (request.getUsername() == null)) {
            throw new UserValidationException("");
        }

        if ((request.getFirstName().isEmpty()) || (request.getFirstName() == null)) {
            throw new UserValidationException("");
        }

        if ((request.getLastName().isEmpty()) || (request.getLastName() == null)) {
            throw new UserValidationException("");
        }

        if ((request.getPassword().isEmpty()) || (request.getPassword() == null)) {
            throw new UserValidationException("");
        }

    }

}
