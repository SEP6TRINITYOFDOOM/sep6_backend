package com.sep6.app.controller;

import com.sep6.app.SearchActor;
import com.sep6.app.SearchMovie;
import com.sep6.app.TrendingMovie;
import com.sep6.app.model.SearchResults;
import com.sep6.app.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping("/search/movie/{param}")
    public ArrayList<SearchMovie> getMovieResults(@PathVariable String param)
    {
        return this.searchService.searchMovies(param);
    }
    @GetMapping("/search/actor/{param}")
    public ArrayList<SearchActor> getActorResults(@PathVariable String param)
    {
        return this.searchService.searchActor(param);
    }
}
