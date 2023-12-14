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


    @CrossOrigin(origins = "http://35.234.87.9:80")
    @GetMapping("/movies")
    public MovieDetails getMovieDetails(@RequestParam String id){
        return this.movieService.getMovieDetails(id);
    }

    @CrossOrigin(origins = "http://35.234.87.9:80")
    @GetMapping("/movies/trending")
    public MovieTMDB[] getTrendingMovies(){
        return this.movieService.getTrendingMovies();
    }

    @CrossOrigin(origins = "http://35.234.87.9:80")
    @GetMapping("/movie/{id}/credits")
    public MovieCredits getCredits(@PathVariable String id){
        return this.movieService.getCastAndCrew(id);
    }

}
