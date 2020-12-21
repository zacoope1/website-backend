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
    @GeneratedValue()
    private int id;

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

    public User(int id, String userUuid, String username, String email, String firstName, String lastName, String accountCreatedDate, String password) {
        this.id = id;
        this.userUuid = userUuid;
        this.username = username.toLowerCase();
        this.email = email.toLowerCase();
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreatedDate = accountCreatedDate;
        this.password = password;
    }

    public User() {
        this.id = 0;
        this.userUuid = "";
        this.username = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.accountCreatedDate = "";
        this.password = "";
    }

}
