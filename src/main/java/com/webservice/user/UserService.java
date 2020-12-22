package com.webservice.user;

import com.webservice.common.Constants;
import com.webservice.common.error.exceptions.DuplicateDataException;
import com.webservice.common.error.exceptions.IncorrectPasswordException;
import com.webservice.common.error.exceptions.UserNotFoundException;
import com.webservice.user.model.User;
import com.webservice.user.model.requests.CreateUserRequest;
import com.webservice.user.model.requests.UserInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserInfo(UserInfoRequest request) throws IncorrectPasswordException, UserNotFoundException {

        Pattern pattern = Pattern.compile(Constants.EMAIL_REGEX_PATTERN);
        Matcher matcher = pattern.matcher(request.getUsername());
        User user;

        try {

            if (matcher.matches()) {
                log.info("Email detected - Getting user entity by email.");
                user = userRepository.getUserInfoByEmail(request.getUsername());
            }
            else {
                log.info("Username detected - Getting user entity by username.");
                user = userRepository.getUserInfoByUsername(request.getUsername());
            }

            if(user == null) {
                log.error("User not found!");
                throw new UserNotFoundException();
            }

            log.info("Retrieved {}", user.getUsername());

            if (user.getPassword().equals(BCrypt.hashpw(request.getPassword(), user.getSalt()))){
                return user;
            }
            else {
                throw new IncorrectPasswordException();
            }
        }
        catch(Exception e) {
            log.error(e.toString());
            throw e;
        }
    }

    public User createUser(CreateUserRequest request) throws Exception {

        LocalDate date = LocalDate.now();
        String dateText = date.toString();

        long startTime = System.nanoTime();
        log.info("Generating password...");
        String salt = BCrypt.gensalt(13);
        String hash = BCrypt.hashpw(request.getPassword(), salt);
        log.info("Password generated. Took elapsed time of: {} ms", ( (System.nanoTime() - (startTime)) / 1000000) );

        User user = User.builder()
                .username(request.getUsername())
                .password(hash)
                .salt(salt)
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
            log.error(e.toString());
            if(e.toString().contains(Constants.DATA_INTEGRITY_VIOLATION_EXCEPTION)){
                if(e.toString().toLowerCase().contains(Constants.USERNAME)){
                    throw new DuplicateDataException("Username is already in use. Please try a new one.");
                }
                else if(e.toString().toLowerCase().contains(Constants.EMAIL)){
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
