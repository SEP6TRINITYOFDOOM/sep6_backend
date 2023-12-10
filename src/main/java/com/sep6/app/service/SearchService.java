package com.sep6.app.service;

import com.sep6.app.Movie;
import com.sep6.app.TrendingMovie;
import com.sep6.app.TrendingMovies;
import com.sep6.app.model.SearchResults;
import com.sep6.app.repository.MovieRepository;
import com.sep6.app.repository.PersonRepository;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

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

    public void searchMovies(String searchParam)
    {
        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc";

        builder.defaultHeader("Token","eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        TrendingMovies tempMovies = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(TrendingMovies.class)
                .block();

        assert tempMovies != null;

        ArrayList<Movie> resultMovies = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.getDefault() );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH

        for (Movie movie: tempMovies.getResults()) {
            if (movie.getTitle().contains(searchParam))
            {
                resultMovies.add(movie);
            }
        }

        searchResults.setMovieResults(resultMovies);
    }
}
