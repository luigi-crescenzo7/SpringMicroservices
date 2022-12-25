package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.IdCardItem;
import it.unisa.tirocinio.beans.VaultItem;
import it.unisa.tirocinio.services.FabricService;
import it.unisa.tirocinio.services.UserService;
import it.unisa.tirocinio.services.VaultItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Controller
@RequestMapping("/app")
@Slf4j
public class MainController {

    private final VaultItemService vaultService;
    private final UserService userService;
    private final FabricService fabricService;


    public MainController(
            final UserService userService,
            final FabricService fabricService,
            final VaultItemService vaultService) {


        this.userService = userService;
        this.fabricService = fabricService;
        this.vaultService = vaultService;
    }

    @GetMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("attributo", "hooray!");
        return "index2";
    }

    @PostMapping("/save-vault-item")
    public ResponseEntity<String> item(@ModelAttribute VaultItem item, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<>("Error mapping VaultItem object", HttpStatus.BAD_REQUEST);

        item.setOwnerId("63834344af957a1c25218b32");

        boolean flag = vaultService.saveItem(item);
        if (!flag) return new ResponseEntity<>("item not saved", HttpStatus.BAD_REQUEST);

        log.info("Item received: " + item);
        return new ResponseEntity<>("item saved", HttpStatus.OK);
    }

    @GetMapping("/vaultItem")
    public String itemPage(Model model) {
        model.addAttribute("VaultItem", new VaultItem());
        return "createVaultItemTemplate";
    }

    @GetMapping("/idCardItem")
    public String cardPage(Model model) {
        model.addAttribute("IdCardItem", new IdCardItem());
        return "createCardTemplate";
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

    @GetMapping(value = "/vault-items")
    public ResponseEntity<List<VaultItem>> items() {
        List<VaultItem> items = vaultService.findAll();
        return new ResponseEntity<>(Objects.requireNonNullElseGet(items, ArrayList::new), HttpStatus.OK);
    }

    @PostMapping(value = "/save-card-item")
    public ResponseEntity<String> card(@ModelAttribute IdCardItem item, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new RuntimeException();

        item.setOwnerId("ownerId1");
        String result = fabricService.saveItem(item);
        if (result == null)
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        log.info("Result: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<String> users() {
        log.info("Call to /users endpoint");
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
