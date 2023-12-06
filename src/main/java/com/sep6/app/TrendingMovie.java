package com.sep6.app;

import java.time.LocalDate;

public class TrendingMovie {

    public TrendingMovie(Integer id,String title,int year, int genre_id, String poster_path){
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre_id = genre_id;
        this.path = poster_path;
        this.created_on = LocalDate.now();
        this.updated_on = LocalDate.now();
    }

    private Integer id;

    private String title;

    private int year;

    private LocalDate created_on;

    private LocalDate updated_on;

    private int genre_id;

    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
