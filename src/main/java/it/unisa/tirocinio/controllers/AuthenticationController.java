package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthenticationController {

    final UserService userService;

    public AuthenticationController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public String login(@RequestParam(name = "email") String email,
                        @RequestParam(name = "password") String password,
                        Model model) {

        System.out.println(email + "  " + password);
        // implement BCrypt hashing.
        boolean flag = userService.login(email, password);
        if (flag) {
            model.addAttribute("auth", "true");
        } else {
            model.addAttribute("auth", "false");
        }
        return "login";
    }
}
