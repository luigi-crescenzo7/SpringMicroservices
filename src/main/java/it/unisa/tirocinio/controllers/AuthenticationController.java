package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.User;
import it.unisa.tirocinio.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/app/auth")
@Slf4j
public class AuthenticationController {

    final UserService userService;

    public AuthenticationController(final UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/register")
    public String register(@ModelAttribute User user, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new RuntimeException();

        log.info("user " + user);
        boolean flag = userService.saveUser(user);

        if (!flag)
            throw new ResponseStatusException(HttpStatusCode.valueOf(500), "user not saved");

        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "email") String email,
                        @RequestParam(name = "password") String password,
                        Model model, HttpSession session) {

        System.out.println(email + "  " + password);
        String userId = userService.login(email, password);
        boolean flag = userId != null;
        if (flag) {
            log.info("User id: " + userId);
            session.setAttribute("user", userId);
            model.addAttribute("auth", "true");
        } else {
            model.addAttribute("auth", "false");
        }
        return "login";
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> users() {
        log.info("Call to /users endpoint");
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
