package com.sep6.app.model;

import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.MovieTMDB;

public class SearchResults
{
    private ActorTMDB[] actorResults;
    private MovieTMDB[] movieResults;

    public SearchResults(ActorTMDB[] actorResults, MovieTMDB[] movieResults)
    {
        this.actorResults = actorResults;
        this.movieResults = movieResults;
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

    public void setMovieResults(MovieTMDB[] movieResults)
    {
        this.movieResults = movieResults;
    }
}
