package com.example.demo.controllers;


import com.example.demo.beans.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
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
    @ResponseBody
    public ResponseEntity<String> authUser(@RequestParam(name = "email") String email,
                                           @RequestParam(name = "passwordHash") String passwordHash) {

        List<User> users = userRepository.findAllUsersWithEmailAndPassword();
        log.info(users.toString());

        Optional<User> userOptional = users.stream().filter(
                (user) -> user.getEmail().equals(email) &&
                        encoder.matches(passwordHash, user.getPassword())).findFirst();

        if (userOptional.isEmpty())
            return new ResponseEntity<>("not authorized", HttpStatus.OK);

        log.info("User is present");
        return new ResponseEntity<>("authorized", HttpStatus.OK);
    }
}