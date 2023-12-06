package com.sep6.app;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
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

}
