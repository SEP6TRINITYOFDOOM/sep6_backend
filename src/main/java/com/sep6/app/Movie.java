package com.sep6.app;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Movie {
    public Movie(){

    }

    boolean adult;
    String backdrop_path;
    int[] genre_ids;
    int id;
    String original_language;
    String original_title;
    String overview;
    float popularity;
    String poster_path;
    String release_date;
    String title;
    boolean video;
    float vote_average;
    int vote_count;
}
