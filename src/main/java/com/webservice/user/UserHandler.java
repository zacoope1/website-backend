package com.webservice.user;

import com.webservice.common.error.DuplicateDataException;
import com.webservice.common.error.IncorrectPasswordException;
import com.webservice.common.error.UserValidationException;
import com.webservice.common.validator.UserValidator;
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
    UserValidator userValidator = new UserValidator();

    public UserInfo getUserInfo(UserInfoRequest request) throws Exception {
        try {
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

    public User createUser(CreateUserRequest request) throws Exception {

        try {
            log.info("Validating Create User Request");
            userValidator.validateCreateUser(request);

            return userService.createUser(request);
        }
        catch(Exception e){
            throw e;
        }

    }

}
