package com.sep6.app.controller;

import com.sep6.app.service.DTO.MovieCredits;
import com.sep6.app.service.DTO.MovieTMDB;
import com.sep6.app.service.MovieService;
import com.sep6.app.service.DTO.MovieDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/movies")
    public MovieDetails getMovieDetails(@RequestParam String id){
        return this.movieService.getMovieDetails(id);
    }

    @GetMapping("/movies/trending")
    public MovieTMDB[] getTrendingMovies(){
        return this.movieService.getTrendingMovies();
    }

    @GetMapping("/movie/{id}/credits")
    public MovieCredits getCredits(@PathVariable String id){
        return this.movieService.getCastAndCrew(id);
    }

}
