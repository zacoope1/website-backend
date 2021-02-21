package com.webservice.session;

import com.webservice.common.Constants;
import com.webservice.common.error.ErrorResponse;
import com.webservice.common.error.exceptions.SessionNotFoundException;
import com.webservice.session.model.SessionCreateRequest;
import com.webservice.session.model.Session;
import com.webservice.session.model.SessionDeleteRequest;
import com.webservice.session.model.SessionResponse;
import com.webservice.user.UserHandler;
import com.webservice.user.model.UserInfo;
import com.webservice.user.model.requests.UserInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.CredentialException;
import java.util.Locale;

@Slf4j
@RestController
public class SessionController {

    @Autowired SessionHandler sessionHandler;
    @Autowired UserHandler userHandler;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> performLogInAndCreateSession(@RequestBody UserInfoRequest request) {

        log.info("Performing login for user: {}", request.getUsername());

        try {
            Session session;
            String userUuid = userHandler.validateUserCredentials(request).getUserUuid();

            if(userUuid != null){
                session = sessionHandler.createSession(SessionCreateRequest.builder().userUuid(userUuid).build());

                SessionResponse response = SessionResponse.builder()
                        .sessionUuid(session.getSessionUuid())
                        .userUuid(session.getUserUuid())
                        .build();

                return new ResponseEntity<SessionResponse>(response, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(Constants.PASSWORD_MISMATCH_MESSAGE).build(), HttpStatus.UNAUTHORIZED);
            }

        }
        catch(SessionNotFoundException e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        catch(CredentialException e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(e.getMessage()).build(), HttpStatus.UNAUTHORIZED);
        }
        catch(Exception e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(e.getMessage().toString()).build(), HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin
    @PostMapping("/logout")
    public ResponseEntity<?> deleteSession(@RequestBody SessionDeleteRequest request){
        try{
            String sessionUuid = sessionHandler.deleteSession(request);
            return new ResponseEntity<Session>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(e.getMessage().toString()).build(), HttpStatus.BAD_REQUEST);
        }
    }

    /* createSession() is an Exposed API for testing purposes only */
    @CrossOrigin
    @PostMapping("/create-session")
    public ResponseEntity<?> createSession(@RequestBody SessionCreateRequest request){

        try{
            Session session = sessionHandler.createSession(request);
            return new ResponseEntity<Session>(session, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage(e.getMessage().toString()).build(), HttpStatus.BAD_REQUEST);
        }

    }

}
