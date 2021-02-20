package com.webservice.session;

import com.webservice.common.error.ErrorResponse;
import com.webservice.session.model.SessionCreateRequest;
import com.webservice.session.model.Session;
import com.webservice.session.model.SessionDeleteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SessionController {

    @Autowired SessionHandler sessionHandler;

    @CrossOrigin
    @PostMapping("/create-session")
    public ResponseEntity<?> createSession(@RequestBody SessionCreateRequest request){

        try{
            Session session = sessionHandler.createSession(request);
            return new ResponseEntity<Session>(session, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage("An invalid request was made").build(), HttpStatus.BAD_REQUEST);
        }


    }

    @CrossOrigin
    @PostMapping("/delete-session")
    public ResponseEntity<?> deleteSession(@RequestBody SessionDeleteRequest request){
        try{
            String sessionUuid = sessionHandler.deleteSession(request);
            return new ResponseEntity<Session>(HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<ErrorResponse>(ErrorResponse.builder().errorMessage("An invalid request was made").build(), HttpStatus.BAD_REQUEST);
        }
    }
}
