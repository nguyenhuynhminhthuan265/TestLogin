package com.test.tbcm.base.model.entity;

import com.test.tbcm.security.SecurityContextUtils;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseFullEntity extends BaseTimeEntity {

    @Column(length = 200)
    protected String createBy;

    @Column(length = 200)
    protected String updateBy;

    @PrePersist
    public void PreAddUser() {
        super.PreAddUser();
        this.createBy = SecurityContextUtils.loggedInUsername();
        this.updateBy = this.createBy;
    }

    @PreUpdate
    public void PreUpdateUser() {
        super.PreUpdateUser();
        this.updateBy = SecurityContextUtils.loggedInUsername();
    }

}
