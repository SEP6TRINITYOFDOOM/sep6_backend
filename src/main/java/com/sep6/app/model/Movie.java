package com.sep6.app.model;

import jakarta.persistence.*;
@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private int year;

    public Movie(String title, int year){
        this.title = title;
        this.year = year;
    }

    public Movie() {

    }

    public Integer getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public int getYear(){
        return year;
    }


}
