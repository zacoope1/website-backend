package com.webservice.user;

import com.webservice.common.error.ErrorResponse;
import com.webservice.common.error.UserValidationException;
import com.webservice.user.model.User;
import com.webservice.user.model.requests.CreateUserRequest;
import com.webservice.user.model.requests.UserInfoRequest;
import com.webservice.user.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired UserHandler userHandler;

    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(@RequestBody UserInfoRequest request) {

        log.info("Getting user info for email: {}", request.getEmail());

        try {

            UserInfo userInfo;
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch(Exception e){

            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage("An invalid request was made").build(), HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/create/user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request){

        log.info("Creating user with username: {} with email: {}", request.getUsername(), request.getEmail());

        try {
            User user = userHandler.createUser(request);
            return new ResponseEntity<User>(user, HttpStatus.CREATED);
        }
        catch (UserValidationException e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage("An invalid request was made").build(), HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage("An error occurred while attempting to create a new user").build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
