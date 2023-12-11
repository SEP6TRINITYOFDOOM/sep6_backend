package com.sep6.app.service;

import com.sep6.app.model.SearchResults;
import com.sep6.app.repository.MovieRepository;
import com.sep6.app.repository.PersonRepository;
import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.ActorsTMDB;
import com.sep6.app.service.DTO.MovieTMDB;
import com.sep6.app.service.DTO.MoviesTMDB;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SearchService
{
    private PersonRepository personRepository;
    private MovieRepository movieRepository;

    private SearchResults searchResults = new SearchResults();

    public SearchService(PersonRepository personRepository, MovieRepository movieRepository)
    {
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
    }

    public PersonRepository getPersonRepository()
    {
        return personRepository;
    }

    public void setPersonRepository(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    public MovieRepository getMovieRepository()
    {
        return movieRepository;
    }

    public void setMovieRepository(MovieRepository movieRepository)
    {
        this.movieRepository = movieRepository;
    }

    public SearchResults searchAll(String searchParam)
    {
        searchMovies(searchParam);
        searchActor(searchParam);
        return searchResults;
    }

    public MovieTMDB[] searchMovies(String searchParam)
    {
        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/search/movie?query=" + searchParam + "&include_adult=false&language=en-US&page=1";

        builder.defaultHeader("Token","eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        MoviesTMDB tempMovies = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(MoviesTMDB.class)
                .block();

        assert tempMovies != null;

        searchResults.setMovieResults(tempMovies.getResults());

        return tempMovies.getResults();
    }

    public ActorTMDB[] searchActor(String searchParam)
    {
        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/search/person?query=" + searchParam + "&include_adult=false&language=en-US&page=1";

        ActorsTMDB tempActors = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(ActorsTMDB.class)
                .block();

        assert tempActors != null;

        searchResults.setActorResults(tempActors.getResults());

        return tempActors.getResults();
    }
}
