package com.vigo.coinx.model.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;

    private LocalDateTime createdOn;

    private LocalDateTime modifiedOn;

    private String createdBy;

    private String modifiedBy;

    @PrePersist
    private void setCreatedOn(){
        this.createdOn = LocalDateTime.now();
    }
}
