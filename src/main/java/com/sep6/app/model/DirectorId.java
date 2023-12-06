package com.sep6.app.model;

import jakarta.persistence.Column;

import java.io.Serializable;

public class DirectorId implements Serializable {
    private Integer movie_id;
    private Integer person_id;

    public DirectorId(Integer movie_id, Integer person_id) {
        this.movie_id = movie_id;
        this.person_id = person_id;
    }

    public DirectorId(){

    }
}
