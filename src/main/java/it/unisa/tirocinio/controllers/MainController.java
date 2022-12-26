package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.User;
import it.unisa.tirocinio.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/app")
@Slf4j
public class MainController {
    private final UserService userService;


    public MainController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("attributo", "hooray!");
        return "index2";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("User", new User());
        return "registration";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/users")
    public ResponseEntity<String> users() {
        log.info("Call to /users endpoint");
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
