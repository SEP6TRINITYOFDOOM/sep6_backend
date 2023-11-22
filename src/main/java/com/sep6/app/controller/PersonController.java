package com.sep6.app.controller;

import com.sep6.app.model.Person;
import com.sep6.app.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/people")
    public Iterable<Person> findAllPeople(){
        return this.personRepository.findAll();
    }
}
