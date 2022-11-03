package com.assignment.interpark.common.message;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseMessage {

    private String message;

    private HttpStatus status;

    public ResponseMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
