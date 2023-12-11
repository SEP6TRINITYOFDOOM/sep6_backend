package com.sep6.app.model;

import com.sep6.app.ActorTMDB;
import com.sep6.app.MovieTMDB;

public class SearchResults
{
    //private ArrayList<SearchActor> actorResults;
    private ActorTMDB[] actorResults;
    //private ArrayList<SearchMovie> movieResults;
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
