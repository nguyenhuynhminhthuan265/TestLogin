package com.test.tbcm.network;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class Response<T, S, K> {

    Boolean status = false;

    int code = 0;

    K meta = null;

    S messages = null;

    T data = null;

}
