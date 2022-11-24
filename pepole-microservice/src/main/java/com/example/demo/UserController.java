package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        log.info("/users/all endpoint");
        return userRepository.findAll();
    }

    @GetMapping("/name")
    public List<User> getUsersByName(@RequestParam(name = "name") String name) {
        log.info("/users/name endpoint");
        return userRepository.findAllByName(name);
    }
}
