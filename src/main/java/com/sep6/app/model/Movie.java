package com.sep6.app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private int year;

    private LocalDate created_on;
    
    private LocalDate updated_on;

    private int genre_id;

    public Movie(String title, int year, int genre_id){
        this.title = title;
        this.year = year;
        this.genre_id = genre_id;
        this.created_on = LocalDate.now();
        this.updated_on = LocalDate.now();
    }

    public Movie(int id, String title, int year, int genre_id ){
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre_id = genre_id;
        this.created_on = LocalDate.now();
        this.updated_on = LocalDate.now();
    }

    public Movie() {

    }
}
