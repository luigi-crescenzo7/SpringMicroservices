package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/app")
public class MainController {

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("User", new User());
        return "registration";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
