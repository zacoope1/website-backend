package com.webservice.session;

import com.webservice.common.error.exceptions.SessionNotFoundException;
import com.webservice.session.model.Session;
import com.webservice.session.model.SessionCreateRequest;
import com.webservice.session.model.SessionDeleteRequest;
import com.webservice.user.UserRepository;
import com.webservice.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class SessionService {

    @Autowired UserRepository userRepository;

    @Autowired SessionRepository sessionRepository;

    public User getUserInfoFromSession(String sessionUuid, String userUuid) throws Exception {

        log.info("Checking database for session {}", sessionUuid);

        try {

            User user;
            Session session = sessionRepository.getSession(sessionUuid);

            if(session == null){
                log.error("Session is not found!");
                throw new SessionNotFoundException();
            }
            else {
                log.info("Session: {} found!", session.getSessionUuid());
            }

            if (session.getUserUuid().equals(userUuid)) {
                log.info("Session matched user.");
                user = userRepository.getUserInfoFromSession(userUuid);
            }
            else {
                log.error("User credentials did not match the session.");
                throw new CredentialException("User credentials did not match the session.");
            }

            if (user != null){
                log.info("Returning user");
                return user;
            }
            else {
                log.error("User is null.");
                throw new SessionNotFoundException();
            }
        }
        catch(Exception e) {
            log.error(e.toString());
            throw e;
        }

    }

    public Session createSession(SessionCreateRequest request) throws Exception{

        log.info("Creating session entity...");

        LocalDate date = LocalDate.now();
        String dateText = date.toString();

        Session session = Session.builder()
                .sessionCreateDate(dateText)
                .userUuid(request.getUserUuid())
                .sessionUuid(UUID.randomUUID().toString())
                .build();

        try {
            sessionRepository.save(session);
            log.info("Session entity saved!");
        }
        catch(Exception e){
            log.error(e.getMessage().toString());
            throw e;
        }

        return session;
    }

    public String deleteSession(SessionDeleteRequest request){
        log.info("Deleting session entity.");
        try {
            Session session = sessionRepository.getSession(request.getSessionUuid());
            sessionRepository.delete(session);
            log.info("Session entity deleted!");
            return request.getSessionUuid();
        }
        catch (Exception e){
            log.error(e.getMessage().toString());
            throw e;
        }

    }

}
