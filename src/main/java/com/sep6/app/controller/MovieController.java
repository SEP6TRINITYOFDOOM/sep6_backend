package com.sep6.app.controller;

import com.sep6.app.service.DTO.MovieCredits;
import com.sep6.app.service.DTO.MovieTMDB;
import com.sep6.app.service.MovieService;
import com.sep6.app.service.DTO.MovieDetails;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/movies")
    public MovieDetails getMovieDetails(@RequestParam String id){
        return this.movieService.getMovieDetails(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/movies/trending")
    public MovieTMDB[] getTrendingMovies(){
        return this.movieService.getTrendingMovies();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/movie/{id}/credits")
    public MovieCredits getCredits(@PathVariable String id){
        return this.movieService.getCastAndCrew(id);
    }

}
