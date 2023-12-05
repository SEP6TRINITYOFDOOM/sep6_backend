package com.sep6.app.service;

import com.sep6.app.model.Movie;
import com.sep6.app.repository.ActorRepository;
import com.sep6.app.repository.GenreRepository;
import com.sep6.app.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j

@Service
public class MovieService
{
    private MovieRepository movieRepository;
    /*private GenreRepository genreRepository;
    private ActorRepository actorRepository;*/

    public MovieService(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    public String sayHello(){
        return "Hello";
    }

    public List<Movie> findAllMovies()
    {
        Iterable<Movie> movies = this.movieRepository.findAll();
        List<Movie> list = new ArrayList<>();
        movies.forEach(list::add);
        return list;
    }

    public List<Movie> getMoviesByTitle(String title)
    {
        return this.movieRepository.findByTitle(title);
    }

    public List<Movie> getMoviesByGenre(int genre)
    {
        return this.movieRepository.findByGenre(genre);
    }
}
