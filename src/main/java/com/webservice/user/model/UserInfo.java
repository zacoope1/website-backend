package com.webservice.user.model;

import lombok.Data;

@Data
public class UserInfo {

    private String username;

    private String email;

    private String userUuid;

    private String firstName;

    private String lastName;

    private String accountCreatedDate;

}
