package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.IdCardItem;
import it.unisa.tirocinio.services.FabricService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app/card")
@Slf4j
public class IdCardController {

    private final FabricService fabricService;


    public IdCardController(final FabricService service) {
        this.fabricService = service;
    }

    @GetMapping("/idCardItem")
    public String cardPage(Model model) {
        model.addAttribute("IdCardItem", new IdCardItem());
        return "createCardTemplate";
    }

    @GetMapping(value = "/assets")
    public String assets(Model model) {
        log.info("/assets");
        List<IdCardItem> result = fabricService.findAllAssets();
        model.addAttribute("assets", result);
        return "assetsTemplate";
    }

    @GetMapping("/ownerId")
    public String assetsByOwnerId(Model model) {
        List<IdCardItem> items = fabricService.findAssetsByOwnerId("ownerId1");
        model.addAttribute("items", items);
        return "show-items";
    }

    @PostMapping(value = "/save-card-item")
    public ResponseEntity<String> card(@ModelAttribute IdCardItem item) {

        item.setOwnerId("ownerId1");
        String result = fabricService.saveItem(item);
        if (result == null)
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);

        log.info("Result: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
