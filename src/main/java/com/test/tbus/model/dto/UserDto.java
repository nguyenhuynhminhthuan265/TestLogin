package com.test.tbus.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.test.tbcm.base.model.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder("id")
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    private Long id;

    private String name;

    private Long idRole;

    private boolean isDelete = false;

    public UserDto(String name, Long idRole) {
        this.name = name;
        this.idRole = idRole;
    }
}
