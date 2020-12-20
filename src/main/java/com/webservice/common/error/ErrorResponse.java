package com.webservice.common.error;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {

    private String errorMessage;

}
