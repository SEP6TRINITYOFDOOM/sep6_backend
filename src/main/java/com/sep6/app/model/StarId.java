package com.sep6.app.model;

import jakarta.persistence.Id;

import java.io.Serializable;

public class StarId implements Serializable {
    @Id
    private Integer movie_id;
    @Id
    private Integer person_id;

    public StarId(Integer movie_id, Integer person_id) {
        this.movie_id = movie_id;
        this.person_id = person_id;
    }

    public StarId(){}

    public static StarId of(Integer movieId, Integer personId){
        return new StarId(movieId, personId);
    }
}
