package com.sep6.app.controller;

import com.sep6.app.TrendingMovie;
import com.sep6.app.service.MovieService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    //private final MovieRepository movieRepository;
    private final MovieService movieService;

    public MovieController(/*MovieRepository movieRepository, */MovieService movieService) {
        //this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

    /*@GetMapping("/sayHello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/movies")
    public Iterable<Movie> findAllMovies(){
        return this.movieRepository.findAll();
    }

    @GetMapping("/movies/{title}")
    public List<Movie> findMovieByTitle(@PathVariable String title){
        return this.movieRepository.findByTitle(title);
    }

    @GetMapping("/movies/{genre}")
    public List<Movie> findMovieByTitle(@PathVariable int genre){
        return this.movieRepository.findByGenre(genre);
    }*/


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/movies/trending")
    public List<TrendingMovie> getTrendingMovies(){
        return this.movieService.getTrendingMovies();
    }

}
