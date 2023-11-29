package com.sep6.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "directors")
@IdClass(DirectorId.class)
public class Director {

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

    public Director(Integer movie_id, Integer person_id){
        this.movie_id = movie_id;
        this.person_id = person_id;
    }

    public Director(){
    }
}
