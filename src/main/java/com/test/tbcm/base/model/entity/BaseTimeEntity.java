package com.test.tbcm.base.model.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.ZonedDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity extends BaseEntity {

    @Column
    protected ZonedDateTime createAt;

    @Column
    protected ZonedDateTime updateAt;

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
