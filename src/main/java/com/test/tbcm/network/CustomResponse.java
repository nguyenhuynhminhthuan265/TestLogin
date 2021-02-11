package com.test.tbcm.network;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CustomResponse<T> extends ResponseEntity<T> {

    public CustomResponse(HttpStatus status) {
        super(status);
    }

    public CustomResponse(T body, HttpStatus status) {
        super(body, status);
    }

    public CustomResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public CustomResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

}
