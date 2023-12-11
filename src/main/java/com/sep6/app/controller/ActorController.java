package com.sep6.app.controller;

import com.sep6.app.Actor;
import com.sep6.app.TrendingActor;
import com.sep6.app.service.ActorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/actors/trending")
    public Actor[] getTrendingActors(){
        return this.actorService.getTrendingActors();
    }

}
