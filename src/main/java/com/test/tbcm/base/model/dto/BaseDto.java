package com.test.tbcm.base.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@MappedSuperclass
@Data
public class BaseDto {
}
