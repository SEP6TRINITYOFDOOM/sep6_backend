package com.sep6.app.controller;

import com.sep6.app.controller.request.AddMovieToFavouritesRequest;
import com.sep6.app.model.FavouriteMovie;
import com.sep6.app.repository.FavouriteUserMovieRepository;
import com.sep6.app.service.DTO.MovieDetails;
import com.sep6.app.service.DTO.MovieTMDB;
import com.sep6.app.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourites")
public class FavouritesController {


    private final FavouriteUserMovieRepository favouriteUserMovieRepository;
    private final MovieService movieService;

    public FavouritesController(FavouriteUserMovieRepository favouriteUserMovieRepository, MovieService movieService) {
        this.favouriteUserMovieRepository = favouriteUserMovieRepository;
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody AddMovieToFavouritesRequest request) {
        try {
            favouriteUserMovieRepository.save(new FavouriteMovie(request.userId(), request.movieId(), request.position()));
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Could not add movie to favourites: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<MovieTMDB>> get(@RequestParam int userId) {
        List<Integer> movieIds = favouriteUserMovieRepository.findByUserId(userId).stream().map(FavouriteMovie::getMovie_id).toList();

        List<MovieDetails> favouriteMovieDetails = movieIds.stream().map(id -> movieService.getMovieDetails(String.valueOf(id))).toList();

        return ResponseEntity.ok(
                favouriteMovieDetails.stream()
                        .map(movieDetails ->
                                new MovieTMDB(movieDetails.id, movieDetails.title, movieDetails.poster_path))
                        .toList());

    }
}
