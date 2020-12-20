package com.webservice.user;

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

    public User createUser(CreateUserRequest request){

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
            log.error("An error ocurred while creating user.");
            log.error(e.toString());
            throw e;
        }

    }

}
