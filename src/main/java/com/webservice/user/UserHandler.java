package com.webservice.user;

import com.webservice.common.error.UserValidationException;
import com.webservice.common.validator.UserValidator;
import com.webservice.user.model.User;
import com.webservice.user.model.requests.CreateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserHandler {

    @Autowired UserService userService;
    UserValidator userValidator = new UserValidator();

//    public UserInfo getUserInfo(UserInfoRequest request){
//
//    }

    public User createUser(CreateUserRequest request) throws Exception {

        try {
            log.info("Validating Create User Request");
            userValidator.validateCreateUser(request);

            return userService.createUser(request);
        }
        catch(UserValidationException e) {
            throw e;
        }
        catch(Exception e){
            throw e;
        }

    }

}
