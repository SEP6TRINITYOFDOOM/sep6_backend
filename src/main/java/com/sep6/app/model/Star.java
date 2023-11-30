package com.sep6.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "star")
@IdClass(StarId.class)
public class Star {
    @Id
    private Integer movieId;

    @Id
    private Integer personId;

    public Integer getMovieId() {
        return movieId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Star(Integer movieId, Integer personId){
        this.movieId = movieId;
        this.personId = personId;
    }

    public Star(){
    }
}
