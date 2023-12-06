package com.sep6.app.controller;

import com.sep6.app.model.Director;
import com.sep6.app.repository.DirectorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DirectorController {

    private final DirectorRepository directorRepository;


    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @GetMapping("/directors")
    public Iterable<Director> getDirectors(){
        return this.directorRepository.findAll();
    }
}
