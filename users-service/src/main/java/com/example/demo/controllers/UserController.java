package com.example.demo.controllers;


import com.example.demo.beans.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<User>> getUsers() {
        log.info("/users/all endpoint");
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    /* inutile
    @GetMapping("/name")
    public ResponseEntity<List<User>> getUsersByName(@RequestParam(name = "name") String name) {
        log.info("/users/name endpoint");
        List<User> user
        return userRepository.findAllByName(name);
    }*/

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody User user) {
        log.info("User received " + user);
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser;
        try {
            savedUser = userRepository.save(user);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("duplicate key email", HttpStatus.BAD_REQUEST);
        }
        log.info("saved user: " + user);
        return new ResponseEntity<>("UserId:" + savedUser.getId(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authUser(@RequestParam(name = "email") String email,
                                           @RequestParam(name = "password") String password) {

        Optional<User> optUser = userRepository.findUserByEmail(email);

        if(optUser.isEmpty()) return new ResponseEntity<>("user does not exist", HttpStatus.BAD_REQUEST);

        User savedUser = optUser.get();

        if (!encoder.matches(password, savedUser.getPassword()))
            return new ResponseEntity<>("not authorized", HttpStatus.OK);

        log.info("User is present");
        return new ResponseEntity<>("authorized", HttpStatus.OK);
    }
}
