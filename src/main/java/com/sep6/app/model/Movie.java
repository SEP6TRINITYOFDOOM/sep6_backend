package com.sep6.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movie")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDate created_on) {
        this.created_on = created_on;
    }

    public LocalDate getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(LocalDate updated_on) {
        this.updated_on = updated_on;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
}
