package com.test.tbms.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder("id")
@NoArgsConstructor
@AllArgsConstructor
public class MessageEntityDto {

    private Long id;


    private String textVI;


    private String textEN;
    /*false : exist in database
     * true : deleted
     * */

//    private boolean isDelete = false;

    public MessageEntityDto(String textVI, String textEN) {
        this.textVI = textVI;
        this.textEN = textEN;
    }
}
