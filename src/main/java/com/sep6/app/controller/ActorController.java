package com.sep6.app.controller;

import com.sep6.app.service.ActorService;
import com.sep6.app.service.DTO.ActorDetails;
import com.sep6.app.service.DTO.ActorTMDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @GetMapping("/actor/{id}")
    public ActorDetails getActorDetails(@PathVariable String id) throws IOException {
        return this.actorService.getActorDetails(id);
    }

    @GetMapping("/actors/trending")
    public ActorTMDB[] getTrendingActors(){
        return this.actorService.getTrendingActors();
    }

}
