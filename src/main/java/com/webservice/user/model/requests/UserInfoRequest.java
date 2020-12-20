package com.webservice.user.model.requests;

import lombok.Data;

@Data
public class UserInfoRequest {

    private String email;

    private String password;

}