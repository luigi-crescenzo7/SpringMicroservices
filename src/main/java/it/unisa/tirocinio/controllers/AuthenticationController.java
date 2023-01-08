package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.User;
import it.unisa.tirocinio.services.CustomResponseException;
import it.unisa.tirocinio.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/app/auth")
public class AuthenticationController {

    final UserService userService;
    final VaultItemController vaultItemController;

    public AuthenticationController(final UserService userService, final VaultItemController controller) {
        this.userService = userService;
        this.vaultItemController = controller;
    }


    @PostMapping(value = "/register")
    public String register(@ModelAttribute User user, Model model) {
        boolean flag = userService.saveUser(user);

        if (!flag)
            throw new CustomResponseException(
                    new ResponseEntity<>("user not saved", HttpStatus.SERVICE_UNAVAILABLE));

        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam(name = "email") String email,
                              @RequestParam(name = "password") String password,
                              Model model, HttpSession session) {

        String userId = userService.login(email, password);
        boolean flag = userId != null;
        if (flag) {
            session.setAttribute("user", userId);
            model.addAttribute("auth", "true");
        } else {
            model.addAttribute("auth", "false");
        }

        return vaultItemController.itemsByOwnerId(null, session);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> users() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
