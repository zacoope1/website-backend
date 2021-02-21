package com.webservice.user;

import com.webservice.common.error.exceptions.IncorrectPasswordException;
import com.webservice.common.error.exceptions.UserNotFoundException;
import com.webservice.common.error.exceptions.UserValidationException;
import com.webservice.common.validator.UserRequestValidator;
import com.webservice.session.SessionService;
import com.webservice.user.model.User;
import com.webservice.user.model.UserInfo;
import com.webservice.user.model.requests.CreateUserRequest;
import com.webservice.user.model.requests.UserInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserHandler {

    @Autowired UserService userService;
    @Autowired SessionService sessionService;
    UserRequestValidator userRequestValidator = new UserRequestValidator();

    public User validateUserCredentials(UserInfoRequest request) throws Exception {

        log.info("Validating user credentials...");

        try {
            userRequestValidator.validateGetUserInfoRequest(request);
            User user = userService.getUserInfo(request);

            if (user != null) {
                log.info("Credentials Valid!");
                return user;
            } else {
                log.error("Credentials invalid!");
                return null;
            }

        }
        catch(Exception e){
            log.error(e.getMessage().toString());
            throw e;
        }

    }

    public UserInfo getUserInfo(UserInfoRequest request) throws Exception {
        try {
            userRequestValidator.validateGetUserInfoRequest(request);
            User user = userService.getUserInfo(request);
            return UserInfo.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .userUuid(user.getUserUuid())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .accountCreatedDate(user.getAccountCreatedDate())
                    .build();
        }
        catch(Exception e){
            throw e;
        }
    }

    public UserInfo getUserInfoFromSession(String sessionUuid, String userUuid) throws Exception {
        try {
            User user = sessionService.getUserInfoFromSession(sessionUuid, userUuid);
            return UserInfo.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .userUuid(user.getUserUuid())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .accountCreatedDate(user.getAccountCreatedDate())
                    .build();
        }
        catch(Exception e){
            throw e;
        }
    }

    public User createUser(CreateUserRequest request) throws Exception {

        try {
            log.info("Validating Create User Request");
            userRequestValidator.validateCreateUserRequest(request);

            return userService.createUser(request);
        }
        catch(Exception e){
            throw e;
        }

    }

}
