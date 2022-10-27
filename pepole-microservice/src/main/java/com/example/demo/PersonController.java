package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    PersonRespository repository;

    public PersonController(final PersonRespository repository) {
        this.repository = repository;
    }

    @GetMapping("all")
    public List<Person> getPersons() {
        return repository.findAll();
    }
}
