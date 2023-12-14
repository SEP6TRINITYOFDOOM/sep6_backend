package com.sep6.app.controller;

import com.sep6.app.service.DTO.MovieCredits;
import com.sep6.app.service.PersonService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @CrossOrigin(origins = "http://35.234.87.9:80")
    @GetMapping("/person/{id}")
    public MovieCredits getPerson(@PathVariable String id) {
        return this.personService.getFeaturedIn(id);
    }
}
