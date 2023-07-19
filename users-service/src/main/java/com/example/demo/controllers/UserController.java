package com.example.demo.controllers;


import com.example.demo.beans.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user-service")
public class UserController {

    final UserRepository userRepository;
    final PasswordEncoder encoder;

    public UserController(final UserRepository userRepository, final PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser;
        try {
            savedUser = userRepository.save(user);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>("duplicate key email", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("UserId:" + savedUser.getId(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authUser(@RequestParam(name = "email") String email,
                                           @RequestParam(name = "password") String password) {

        Optional<User> optUser = userRepository.findUserByEmail(email);

        if (optUser.isEmpty()) return new ResponseEntity<>("user does not exist", HttpStatus.BAD_REQUEST);

        User savedUser = optUser.get();

        if (!encoder.matches(password, savedUser.getPassword()))
            return new ResponseEntity<>("User " + savedUser.getEmail() + " not authorized", HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>("authorized with UserId:" + savedUser.getId(), HttpStatus.OK);
    }
}
