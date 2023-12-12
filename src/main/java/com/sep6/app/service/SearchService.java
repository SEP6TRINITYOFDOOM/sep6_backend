package com.sep6.app.service;

import com.sep6.app.model.SearchResults;
import com.sep6.app.repository.MovieRepository;
import com.sep6.app.repository.PersonRepository;
import com.sep6.app.service.DTO.*;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Service
public class SearchService
{
    @Getter
    private PersonRepository personRepository;
    @Getter
    private MovieRepository movieRepository;

    private SearchResults searchResults = new SearchResults();

    public SearchService(PersonRepository personRepository, MovieRepository movieRepository)
    {
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
    }

    public void setPersonRepository(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
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

    public SearchResults searchAllWithGenre(String searchParam, int genreId)
    {
        searchMovieWithGenre(searchParam, genreId);
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

        ArrayList<Integer> tempGenres = new ArrayList<>();

        for(int i = 0 ; i < tempMovies.getResults().length ; i++){
            for(int j = 0 ; j < tempMovies.getResults()[i].getGenre_ids().length ; j++){
                addUniqueGenre(tempGenres, tempMovies.getResults()[i].getGenre_ids()[j] );
            }
        }

        Integer[] genres = new Integer[tempGenres.size()];

        for (int i = 0 ; i < tempGenres.size() ; i++){
            genres[i] = tempGenres.get(i);
        }

        searchResults.setGenres(genres);

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


        for(int i = 0 ; i < tempActors.getResults().length ; i++){
            if(tempActors.getResults()[i].getProfile_path() == null){
                tempActors.getResults()[i].setProfile_path("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
            }else {
                tempActors.getResults()[i].setProfile_path("https://image.tmdb.org/t/p/w500" + tempActors.getResults()[i].getProfile_path());
            }
        }

        assert tempActors != null;

        searchResults.setActorResults(tempActors.getResults());

        return tempActors.getResults();
    }

    public MovieTMDB[] searchMovieWithGenre(String searchParam, int genreId){
        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/search/movie?query=" + searchParam + "&include_adult=false&language=en-US&page=1";

        builder.defaultHeader("Token","eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        MoviesTMDB tempMovies = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(MoviesTMDB.class)
                .block();

        assert tempMovies != null;

        ArrayList<MovieTMDB> tempResults = new ArrayList<>();
        ArrayList<Integer> tempGenres = new ArrayList<>();

        for(int i = 0 ; i < tempMovies.getResults().length ; i++){
            for(int j = 0 ; j < tempMovies.getResults()[i].getGenre_ids().length ; j++){
                addUniqueGenre(tempGenres, tempMovies.getResults()[i].getGenre_ids()[j] );
                if(tempMovies.getResults()[i].getGenre_ids()[j] == genreId){
                    tempResults.add(tempMovies.getResults()[i]);
                }
            }
        }

        MovieTMDB[] results = new MovieTMDB[tempResults.size()];
        Integer[] genres = new Integer[tempGenres.size()];

        for (int i = 0 ; i < tempResults.size() ; i++){
            results[i] = tempResults.get(i);
        }

        for (int i = 0 ; i < tempGenres.size() ; i++){
            genres[i] = tempGenres.get(i);
        }

        searchResults.setMovieResults(results);
        searchResults.setGenres(genres);

        return results;
    }

    private static void addUniqueGenre(ArrayList<Integer> list, Integer genre) {
        if (!list.contains(genre)) {
            list.add(genre);
        }
    }

}
