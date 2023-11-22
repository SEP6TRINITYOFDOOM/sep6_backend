package com.sep6.app.controller;

import com.sep6.app.model.Movie;
import com.sep6.app.repository.MovieRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/sayHello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/movies")
    public Iterable<Movie> findAllMovies(){
        return this.movieRepository.findAll();
    }



}
