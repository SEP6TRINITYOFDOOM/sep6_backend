package com.sep6.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Actor {

    @Id
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
