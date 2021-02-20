package com.webservice.session.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name="session_table")
public class Session {

    @Id
    @GeneratedValue()
    private int id;

    @Column(name="session_uuid")
    private String sessionUuid;

    @Column(name="user_uuid")
    private String userUuid;

    @Column(name="session_create_date")
    private String sessionCreateDate;

    public Session(){
        this.id = 0;
        this.sessionUuid = "";
        this.userUuid = "";
        this.sessionCreateDate = "";
    }

    public Session(int id, String sessionUuid, String userUuid, String sessionCreateDate){
        this.id = id;
        this.sessionUuid = sessionUuid;
        this.userUuid = userUuid;
        this.sessionCreateDate = sessionCreateDate;
    }

}
