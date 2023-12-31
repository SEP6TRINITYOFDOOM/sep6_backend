package com.sep6.app.service;

import com.sep6.app.service.DTO.ActorDetails;
import com.sep6.app.service.DTO.ActorTMDB;
import com.sep6.app.service.DTO.ActorsTMDB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class ActorService {

    public ActorService(){
    }

    public ActorDetails getActorDetails(String id){

        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/person/" + id +"?language=en-US";

        ActorDetails actorDetails = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(ActorDetails.class)
                .block();

        assert actorDetails != null;
        return actorDetails;
    }

    public ActorTMDB[] getTrendingActors(){

        WebClient.Builder builder = WebClient.builder();

        String url = "https://api.themoviedb.org/3/person/popular?language=en-US&page=1";

        builder.defaultHeader("Token","eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        ActorsTMDB tempActorsTMDB = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(ActorsTMDB.class)
                .block();

        assert tempActorsTMDB != null;

        return tempActorsTMDB.getResults();

    }



}
