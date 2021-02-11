package com.test.tbms.model.entity;

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
@Table(name = "TBMS_MESSAGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageEntity extends BaseEntity {

    @Id
    private Long id;

    @Column(name = "text_VI", length = 500)
    private String textVI;

    @Column(name = "text_EN", length = 500)
    private String textEN;
    /*false : exist in database
     * true : deleted
     * */
    @Column(name = "is_Delete")
    private boolean isDelete = false;

    public MessageEntity(Long id, String textVI, String textEN) {
        this.id = id;
        this.textVI = textVI;
        this.textEN = textEN;
    }
}
