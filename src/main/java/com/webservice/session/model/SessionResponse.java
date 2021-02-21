package com.webservice.session.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SessionResponse {

    public String sessionUuid;
    public String userUuid;

}
