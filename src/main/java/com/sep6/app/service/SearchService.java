package com.sep6.app.service;

import com.sep6.app.controller.request.FriendResponse;
import com.sep6.app.controller.request.UserResponse;
import com.sep6.app.model.Friendship;
import com.sep6.app.model.SearchResults;
import com.sep6.app.repository.FriendshipRepository;
import com.sep6.app.repository.user.UserRepository;
import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.ActorsTMDB;
import com.sep6.app.service.DTO.MovieTMDB;
import com.sep6.app.service.DTO.MoviesTMDB;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {
    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    private final SearchResults searchResults = new SearchResults();

    public SearchService(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;

        this.friendshipRepository = friendshipRepository;
    }

    public SearchResults searchAll(String searchParam) {
        searchMovies(searchParam);
        searchActor(searchParam);
        searchUsers(searchParam);
        return searchResults;
    }

    public SearchResults searchAllWithGenre(String searchParam, int genreId) {
        searchMovieWithGenre(searchParam, genreId);
        searchActor(searchParam);
        return searchResults;
    }

    public MovieTMDB[] searchMovies(String searchParam) {
        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/search/movie?query=" + searchParam + "&include_adult=false&language=en-US&page=1";

        builder.defaultHeader("Token", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        MoviesTMDB tempMovies = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(MoviesTMDB.class)
                .block();

        assert tempMovies != null;

        ArrayList<Integer> tempGenres = new ArrayList<>();

        for (int i = 0; i < tempMovies.getResults().length; i++) {
            for (int j = 0; j < tempMovies.getResults()[i].getGenre_ids().length; j++) {
                addUniqueGenre(tempGenres, tempMovies.getResults()[i].getGenre_ids()[j]);
            }
        }

        Integer[] genres = new Integer[tempGenres.size()];

        for (int i = 0; i < tempGenres.size(); i++) {
            genres[i] = tempGenres.get(i);
        }

        searchResults.setGenres(genres);

        searchResults.setMovieResults(tempMovies.getResults());

        return tempMovies.getResults();
    }

    public ActorTMDB[] searchActor(String searchParam) {
        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/search/person?query=" + searchParam + "&include_adult=false&language=en-US&page=1";

        ActorsTMDB tempActors = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(ActorsTMDB.class)
                .block();


        for (int i = 0; i < tempActors.getResults().length; i++) {
            if (tempActors.getResults()[i].getProfile_path() == null) {
                tempActors.getResults()[i].setProfile_path("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
            } else {
                tempActors.getResults()[i].setProfile_path("https://image.tmdb.org/t/p/w500" + tempActors.getResults()[i].getProfile_path());
            }
        }

        assert tempActors != null;

        searchResults.setActorResults(tempActors.getResults());

        return tempActors.getResults();
    }

    public void searchMovieWithGenre(String searchParam, int genreId) {
        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/search/movie?query=" + searchParam + "&include_adult=false&language=en-US&page=1";

        builder.defaultHeader("Token", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        MoviesTMDB tempMovies = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(MoviesTMDB.class)
                .block();

        assert tempMovies != null;

        ArrayList<MovieTMDB> tempResults = new ArrayList<>();
        ArrayList<Integer> tempGenres = new ArrayList<>();

        for (int i = 0; i < tempMovies.getResults().length; i++) {
            for (int j = 0; j < tempMovies.getResults()[i].getGenre_ids().length; j++) {
                addUniqueGenre(tempGenres, tempMovies.getResults()[i].getGenre_ids()[j]);
                if (tempMovies.getResults()[i].getGenre_ids()[j] == genreId) {
                    tempResults.add(tempMovies.getResults()[i]);
                }
            }
        }

        MovieTMDB[] results = new MovieTMDB[tempResults.size()];
        Integer[] genres = new Integer[tempGenres.size()];

        for (int i = 0; i < tempResults.size(); i++) {
            results[i] = tempResults.get(i);
        }

        for (int i = 0; i < tempGenres.size(); i++) {
            genres[i] = tempGenres.get(i);
        }

        searchResults.setMovieResults(results);
        searchResults.setGenres(genres);

    }

    public void searchUsers(String searchParam) {
        searchResults.setUsers(
                findUsers(searchParam).toArray(new FriendResponse[0]));
    }

    private List<FriendResponse> findUsers(String searchParam) {
        return this.userRepository.findByUsernameLike(searchParam).stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse(user.getUsername(), user.getId());
                    Optional<Friendship> f = friendshipRepository.findByRequester_IdOrTarget_Id(user.getId(), user.getId());

                    return f.map(friendship ->
                            new FriendResponse(friendship.getStatus().toString(), friendship.getId(), userResponse, friendship.getRequester().getId()))
                            .orElseGet(() -> new FriendResponse(null, -1, userResponse, -1));
                }).toList();
    }

    private static void addUniqueGenre(ArrayList<Integer> list, Integer genre) {
        if (!list.contains(genre)) {
            list.add(genre);
        }
    }

}
