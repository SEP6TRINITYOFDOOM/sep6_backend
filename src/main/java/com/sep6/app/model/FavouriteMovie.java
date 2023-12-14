package com.sep6.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "favourite_user_movie")
@Table(name = "favourite_user_movie")
public class FavouriteMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer userId;
    private Integer movie_id;

    private Integer position;

    private LocalDate created_on;

    private LocalDate updated_on;

    public FavouriteMovie() {
    }

    public FavouriteMovie(Integer user_id, Integer movie_id, Integer position) {
        this.userId = user_id;
        this.movie_id = movie_id;
        this.position = position;
        this.created_on = LocalDate.now();
        this.updated_on = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }
}
