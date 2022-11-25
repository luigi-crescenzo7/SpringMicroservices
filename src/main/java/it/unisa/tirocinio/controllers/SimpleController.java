package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.services.UserService;
import it.unisa.tirocinio.services.SimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


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

    @GetMapping("login")
    public String login(Model model) {
        return "login";
    }


    @GetMapping(value = "/json")
    @ResponseBody
    public ResponseEntity<String> json() {
        log.info("Call to /json endpoint");
        /*String tmp = service.getJSON();
        model.addAttribute("json_data", tmp);*/
        return new ResponseEntity<>(service.getJSON(), HttpStatus.OK);
    }

    @GetMapping(value = "/peoples")
    @ResponseBody
    public ResponseEntity<String> persons() {
        log.info("Call to /peoples endpoint");
        return new ResponseEntity<>(userService.getPersons(), HttpStatus.OK);
    }
}
