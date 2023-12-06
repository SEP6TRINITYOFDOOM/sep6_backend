package com.sep6.app;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrendingActors {
    public TrendingActors(){

    }

    int page;
    Actor[] results;
    int total_pages;
    int total_results;

}
