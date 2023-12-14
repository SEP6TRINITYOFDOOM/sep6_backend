package com.sep6.app.service;

import com.sep6.app.service.DTO.Cast;
import com.sep6.app.service.DTO.Crew;
import com.sep6.app.service.DTO.MovieCredits;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;

@Slf4j
@Service
public class PersonService {

    public PersonService(){
    }

    public MovieCredits getFeaturedIn(String id){
        WebClient.Builder builder = WebClient.builder().codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024));

        String url = "https://api.themoviedb.org/3/person/"+ id +"/movie_credits?language=en-US";

        builder.defaultHeader("Token","eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk");

        MovieCredits movieCredits;

        movieCredits = builder.build().get()
                .uri(url).headers(h -> h.setBearerAuth("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwZGMyOGVlZWNkZGFiMzE4M2I0NmFmY2U3YzgxNmE1MCIsInN1YiI6IjY1NjliYTRiNjM1MzZhMDEzOTU0NjMzNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RFgKOBdGyNPT6pw5lMqV8k7gtOQxhjPgRWL307fh9Mk"))
                .retrieve()
                .bodyToMono(MovieCredits.class)
                .block();

        assert movieCredits != null;

        Crew[] crewNotAdult = movieCredits.getCrew();
        crewNotAdult = Arrays.stream(crewNotAdult).filter(x -> !x.isAdult()).toArray(Crew[]::new);

        Cast[] castNotAdult = movieCredits.getCast();
        castNotAdult = Arrays.stream(castNotAdult).filter(x -> !x.isAdult()).toArray(Cast[]::new);

        movieCredits.setCrew( crewNotAdult);
        movieCredits.setCast(castNotAdult);

        return movieCredits;
    }

}
