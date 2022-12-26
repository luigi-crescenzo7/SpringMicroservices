package com.example.demo.controllers;


import com.example.demo.beans.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-service")
@Slf4j
public class UserController {

    final UserRepository userRepository;
    final PasswordEncoder encoder;

    public UserController(final UserRepository userRepository, final PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
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
/*
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<User> register(User) {

    }*/

    @PostMapping("/login")
    public ResponseEntity<String> authUser(@RequestParam(name = "email") String email,
                                           @RequestParam(name = "passwordHash") String passwordHash) {

        Optional<User> optUser = userRepository.findUserByEmail(email);

        User savedUser = optUser
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(200),
                        String.format("User with email %s does not exist", email)));

        if (!encoder.matches(passwordHash, savedUser.getPassword()))
            return new ResponseEntity<>("not authorized", HttpStatus.OK);

        log.info("User is present");
        return new ResponseEntity<>("authorized", HttpStatus.OK);
    }
}
