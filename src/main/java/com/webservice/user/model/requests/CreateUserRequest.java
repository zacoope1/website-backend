package com.webservice.user.model.requests;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

}
