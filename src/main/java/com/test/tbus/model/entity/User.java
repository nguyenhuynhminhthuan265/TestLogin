package com.test.tbus.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.test.tbcm.base.model.entity.BaseFullEntity;
import com.test.tbcm.config.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.*;
import java.time.ZonedDateTime;


@Data
@Table(name = "TBUS_USER", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class User extends BaseFullEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String email;

    private String name;

    private String password;

    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "access_token")
    private String accessToken;


    private ZonedDateTime expiredToken;

    @Column(name = "is_Delete")
    private boolean isDelete = false;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_role", insertable = false, updatable = false)
    private Role role;


    public User(String email, String name, Long idRole) {
        this.email = email;
        this.name = name;

        this.idRole = idRole;
    }

    @PrePersist
    public void PreAddUser() {
        super.PreAddUser();
    }

    @PreUpdate
    public void PreUpdateUser() {
        super.PreUpdateUser();

    }

}
