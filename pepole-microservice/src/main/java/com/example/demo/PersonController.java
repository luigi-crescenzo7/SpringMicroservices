package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persons")
@Slf4j
public class PersonController {

    PersonRespository repository;

    public PersonController(final PersonRespository repository) {
        this.repository = repository;
    }

    @GetMapping("all")
    public List<Person> getPersons() {
        log.info("/persons/all endpoint");
        return repository.findAll();
    }
}
