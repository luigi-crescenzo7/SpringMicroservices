package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.services.UserService;
import it.unisa.tirocinio.services.SimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/app")
@Slf4j
public class SimpleController {

    private final SimpleService service;
    private final UserService userService;


    public SimpleController(final SimpleService service, final UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("attributo", "hooray!");
        return "index2";
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


    @GetMapping(value = "/json")
    @ResponseBody
    public ResponseEntity<String> json() {
        log.info("Call to /json endpoint");
        return new ResponseEntity<>(service.getJSON(), HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<String> persons() {
        log.info("Call to /peoples endpoint");
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
