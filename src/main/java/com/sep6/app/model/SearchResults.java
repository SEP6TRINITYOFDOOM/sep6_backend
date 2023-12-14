package com.sep6.app.model;

import com.sep6.app.controller.request.FriendResponse;
import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.MovieTMDB;
import lombok.Setter;

@Setter
public class SearchResults
{
    private ActorTMDB[] actorResults;
    private MovieTMDB[] movieResults;
    private Integer[] genres;
    private FriendResponse[] users;

    public SearchResults(ActorTMDB[] actorResults, MovieTMDB[] movieResults, Integer[] genres, FriendResponse[] users)
    {
        this.actorResults = actorResults;
        this.movieResults = movieResults;
        this.genres = genres;
        this.users = users;
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

    public void setUsers(FriendResponse[] users) {
        this.users = users;
    }

    public FriendResponse[] getUsers() {
        return users;
    }
}
