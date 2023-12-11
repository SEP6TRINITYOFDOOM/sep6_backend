package com.sep6.app.model;

import java.util.ArrayList;
import java.util.List;

import com.sep6.app.*;
import com.sep6.app.Movie;

public class SearchResults
{
    private ArrayList<SearchActor> actorResults;
    private ArrayList<SearchMovie> movieResults;

    public SearchResults(ArrayList<SearchActor> actorResults, ArrayList<SearchMovie> movieResults)
    {
        this.actorResults = actorResults;
        this.movieResults = movieResults;
    }

    public SearchResults()
    {
        this.actorResults = new ArrayList<>();
        this.movieResults = new ArrayList<>();
    }

    public ArrayList<SearchActor> getActorResults()
    {
        return actorResults;
    }

    public void setActorResults(ArrayList<SearchActor> actorResults)
    {
        this.actorResults = actorResults;
    }

    public ArrayList<SearchMovie> getMovieResults()
    {
        return movieResults;
    }

    public void setMovieResults(ArrayList<SearchMovie> movieResults)
    {
        this.movieResults = movieResults;
    }
}
