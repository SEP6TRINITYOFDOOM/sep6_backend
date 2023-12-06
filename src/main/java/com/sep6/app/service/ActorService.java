package com.sep6.app.service;

import com.sep6.app.TrendingActor;
import com.sep6.app.TrendingActors;
import com.sep6.app.repository.ActorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository){
        this.actorRepository = actorRepository;
    }

    public List<TrendingActor> getTrendingActors(){

        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/person/popular?language=en-US&page=1";

        builder.defaultHeader("Token","eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        TrendingActors tempTrendingActors = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(TrendingActors.class)
                .block();

        assert tempTrendingActors != null;

        ArrayList<TrendingActor> trendingActors = new ArrayList<>();

        for(int i = 0 ; i < 6 ; i++){
            trendingActors.add(
                    new TrendingActor(
                            tempTrendingActors.getResults()[i].getId(),
                            tempTrendingActors.getResults()[i].getName(),
                            tempTrendingActors.getResults()[i].getProfile_path()
                    )
            );
        }

        return trendingActors;

    }

}
