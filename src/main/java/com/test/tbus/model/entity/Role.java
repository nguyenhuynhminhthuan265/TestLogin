package com.test.tbus.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.tbcm.base.model.entity.BaseEntity;
import com.test.tbcm.config.Route;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBUS_ROLE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String name;

    @Column(name = "is_Delete")
    private boolean isDelete = false;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users;


    public Role(String name) {
        this.name = name;
    }
}
