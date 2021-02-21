package com.webservice.session;


import com.webservice.session.model.Session;
import com.webservice.session.model.SessionCreateRequest;
import com.webservice.session.model.SessionDeleteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SessionHandler {

    @Autowired SessionService sessionService;

    public Session createSession(SessionCreateRequest request) throws Exception {

        try {

            return sessionService.createSession(request);

        }
        catch (Exception e) {
            throw e;
        }

    }

    public String deleteSession(SessionDeleteRequest request) throws Exception {

        try {
            return sessionService.deleteSession(request);
        }
        catch (Exception e) {
            throw e;
        }

    }

}
