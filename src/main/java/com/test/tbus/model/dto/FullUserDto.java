package com.test.tbus.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder("id")
@NoArgsConstructor
@AllArgsConstructor
public class FullUserDto extends UserDto {

    private String email;

    private String password;

    private RoleDto role;

    private String accessToken;

    private ZonedDateTime expiredToken;

    public FullUserDto(String email, String name, String password, Long idRole) {
        super(name, idRole);
        this.email = email;
        this.password = password;
    }
}
