package com.sep6.app.model;

import java.util.ArrayList;
import java.util.List;
import com.sep6.app.Movie;

public class SearchResults
{
    private ArrayList<Actor> actorResults;
    private ArrayList<Movie> movieResults;

    public SearchResults(ArrayList<Actor> personResults, ArrayList<Movie> movierResults)
    {
        this.actorResults = personResults;
        this.movieResults = movierResults;
    }

    public SearchResults()
    {
        this.actorResults = new ArrayList<>();
        this.movieResults = new ArrayList<>();
    }

    public ArrayList<Actor> getPersonResults()
    {
        return actorResults;
    }

    public void setPersonResults(ArrayList<Actor> personResults)
    {
        this.actorResults = personResults;
    }

    public ArrayList<Movie> getMovieResults()
    {
        return movieResults;
    }

    public void setMovieResults(ArrayList<Movie> movieResults)
    {
        this.movieResults = movieResults;
    }
}
