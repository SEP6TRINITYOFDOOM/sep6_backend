package com.sep6.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "stars")
@IdClass(StarId.class)
public class Star {
    @Id
    private Integer movie_id;

    @Id
    private Integer person_id;

    public Integer getMovieId() {
        return movie_id;
    }

    public Integer getPersonId() {
        return person_id;
    }

    public Star(Integer movie_id, Integer person_id){
        this.movie_id = movie_id;
        this.person_id = person_id;
    }

    public Star(){
    }
}
