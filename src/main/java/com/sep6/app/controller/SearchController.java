package com.sep6.app.controller;

import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.MovieTMDB;
import com.sep6.app.model.SearchResults;
import com.sep6.app.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController
{
    private SearchService searchService;

    public SearchController(SearchService searchService)
    {
        this.searchService = searchService;
    }

    @GetMapping("/search/{param}")
    public SearchResults getAllResults(@PathVariable String param)
    {
        return this.searchService.searchAll(param);
    }

    @GetMapping("/search/{param}/{genre}")
    public SearchResults getAllResultsWithGenre(@PathVariable String param, @PathVariable int genre)
    {
        return this.searchService.searchAllWithGenre(param, genre);
    }
    @GetMapping("/search/movie/{param}")
    public MovieTMDB[] getMovieResults(@PathVariable String param)
    {
        return this.searchService.searchMovies(param);
    }
    @GetMapping("/search/actor/{param}")
    public ActorTMDB[] getActorResults(@PathVariable String param)
    {
        return this.searchService.searchActor(param);
    }
}
