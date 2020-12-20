package com.webservice.user.model;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Builder
@Data
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @Column(name="user_uuid")
    private String userUuid;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="account_created_date")
    private String accountCreatedDate;

    @Column(name="password")
    private String password;

}
