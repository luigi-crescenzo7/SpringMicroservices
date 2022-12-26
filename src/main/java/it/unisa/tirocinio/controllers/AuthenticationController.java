package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.User;
import it.unisa.tirocinio.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
                        Model model) {

        System.out.println(email + "  " + password);
        boolean flag = userService.login(email, password);
        if (flag) {
            model.addAttribute("auth", "true");
        } else {
            model.addAttribute("auth", "false");
        }
        return "login";
    }
}
