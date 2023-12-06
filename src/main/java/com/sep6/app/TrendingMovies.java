package com.sep6.app;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrendingMovies {

    public TrendingMovies(){

    }

    int page;
    Movie[] results;
    int total_pages;
    int total_results;

}
