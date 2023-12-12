package com.sep6.app.model;

import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.MovieTMDB;

public class SearchResults
{
    private ActorTMDB[] actorResults;
    private MovieTMDB[] movieResults;
    private Integer[] genres;

    public SearchResults(ActorTMDB[] actorResults, MovieTMDB[] movieResults, Integer[] genres)
    {
        this.actorResults = actorResults;
        this.movieResults = movieResults;
        this.genres = genres;
    }

    public SearchResults(){
    }

    public ActorTMDB[] getActorResults()
    {
        return actorResults;
    }

    public void setActorResults(ActorTMDB[] actorResults)
    {
        this.actorResults = actorResults;
    }

    public MovieTMDB[] getMovieResults()
    {
        return movieResults;
    }

    public Integer[] getGenres() {
        return genres;
    }

    public void setGenres(Integer[] genres) {
        this.genres = genres;
    }

    public void setMovieResults(MovieTMDB[] movieResults)
    {
        this.movieResults = movieResults;
    }
}
