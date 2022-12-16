package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.VaultItem;
import it.unisa.tirocinio.services.FabricService;
import it.unisa.tirocinio.services.UserService;
import it.unisa.tirocinio.services.SimpleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/app")
@Slf4j
public class SimpleController {

    private final SimpleService service;
    private final UserService userService;
    private final FabricService fabricService;


    public SimpleController(final SimpleService service,
                            final UserService userService,
                            final FabricService fabricService) {

        this.service = service;
        this.userService = userService;
        this.fabricService = fabricService;
    }

    @GetMapping(value = "index")
    public String index(Model model) {
        model.addAttribute("attributo", "hooray!");
        return "index2";
    }

    @PostMapping("/create-item")
    public ResponseEntity<String> item(@ModelAttribute VaultItem item, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<>("Error mapping VaultItem object", HttpStatus.BAD_REQUEST);
        log.info("Item received: " + item.toString());
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/item")
    public String itemPage(Model model) {
        model.addAttribute("VaultItem", new VaultItem());
        return "create-item";
    }

    @GetMapping(value = "/assets")
    public String assets(Model model) {
        log.info("/assets");
        String result = fabricService.findAllAssets();
        model.addAttribute("assets", result);
        return "assetsTemplate";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "registration";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    // DO NOT USE
    @GetMapping(value = "/json")
    @ResponseBody
    public ResponseEntity<String> json() {
        log.info("Call to /json endpoint");
        return new ResponseEntity<>(service.getJSON(), HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    @ResponseBody
    public ResponseEntity<String> users() {
        log.info("Call to /users endpoint");
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
