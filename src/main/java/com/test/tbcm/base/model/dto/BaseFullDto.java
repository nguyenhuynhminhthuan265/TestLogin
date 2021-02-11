package com.test.tbcm.base.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;


@Data
@MappedSuperclass
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseFullDto extends BaseDto {

    private ZonedDateTime createAt;

    private ZonedDateTime updateAt;

    @PrePersist
    public void PreAddUser() {
        this.createAt = ZonedDateTime.now();
        this.updateAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void PreUpdateUser() {
        this.updateAt = ZonedDateTime.now();
    }

}
