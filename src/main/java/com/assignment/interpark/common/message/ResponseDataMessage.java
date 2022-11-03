package com.assignment.interpark.common.message;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseDataMessage<T> extends ResponseMessage {

    private T data;

    public ResponseDataMessage(HttpStatus status, String message, T data) {
        super(status, message);
        this.data = data;
    }

}
