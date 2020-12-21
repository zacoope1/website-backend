package com.webservice.user;

import com.webservice.common.error.DuplicateDataException;
import com.webservice.user.model.User;
import com.webservice.user.model.requests.CreateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(CreateUserRequest request) throws Exception {

        LocalDate date = LocalDate.now();
        String dateText = date.toString();
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .accountCreatedDate(dateText)
                .userUuid(UUID.randomUUID().toString())
                .build();

        try {
            log.info("Creating User...");
            userRepository.save(user);
            log.info("User Created!");
            return user;
        }
        catch (Exception e) {
            log.error("An error occurred while creating user.");
            log.error("__ERROR__: " + e.toString());
            if(e.toString().contains("DataIntegrityViolationException")){
                if(e.toString().toLowerCase().contains("username")){
                    throw new DuplicateDataException("Username is already in use. Please try a new one.");
                }
                else if(e.toString().toLowerCase().contains("email")){
                    throw new DuplicateDataException("Email is already in use. Please try a new one.");
                }
                else{
                    throw new Exception();
                }
            }
            else {
                throw e;
            }

        }

    }

}
