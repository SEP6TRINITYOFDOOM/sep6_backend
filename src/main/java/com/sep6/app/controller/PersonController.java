package com.sep6.app.controller;

import com.sep6.app.model.Person;
import com.sep6.app.service.DTO.MovieCredits;
import com.sep6.app.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/people")
    public Iterable<Person> findAllPeople(){
        return personService.getAll();
    }

    @GetMapping("/person/{id}")
    public MovieCredits getPerson(@PathVariable String id){
        return this.personService.getFeaturedIn(id);
    }
}
