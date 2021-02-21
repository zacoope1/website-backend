package com.webservice.session.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionCreateRequest {
    private String userUuid;
}
