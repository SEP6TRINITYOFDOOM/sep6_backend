package com.sep6.app.controller;


import com.sep6.app.model.Star;
import com.sep6.app.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/star")
public class StarController {

    private final StarRepository starRepository;

    @Autowired
    public StarController(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @GetMapping("/getAll")
    public Iterable<Star> fetchAllRatings() {
        return starRepository.findAll();
    }

}
