package com.test.tbcm.network;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Message {

    long code = 0;

    int type = 0;

    String message = null;

    String intervalMessage = null;

}
