package com.sep6.app.service;

import com.sep6.app.TrendingMovie;
import com.sep6.app.TrendingMovies;
import com.sep6.app.model.Movie;
import com.sep6.app.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    /*public List<Movie> getMoviesByGenre(int genre)
    {
        return this.movieRepository.findMovieByGenre_id(genre);
    }*/

    public List<TrendingMovie> getTrendingMovies(){

        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc";

        builder.defaultHeader("Token","eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        TrendingMovies tempTrendingMovies = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(TrendingMovies.class)
                .block();

        assert tempTrendingMovies != null;

        ArrayList<TrendingMovie> trendingMovies = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        formatter = formatter.withLocale(Locale.getDefault() );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH

        for(int i = 0 ; i < 12 ; i++) {
            trendingMovies.add(new TrendingMovie(
                    tempTrendingMovies.getResults()[i].getId(),
                    tempTrendingMovies.getResults()[i].getTitle(),
                    LocalDate.parse(tempTrendingMovies.getResults()[i].getRelease_date(), formatter).getYear(),
                    tempTrendingMovies.getResults()[i].getGenre_ids()[0],
                    tempTrendingMovies.getResults()[i].getPoster_path()
            ));
        }

        return trendingMovies;

    }
}
