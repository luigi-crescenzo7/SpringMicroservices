package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.IdCardItem;
import it.unisa.tirocinio.services.CustomResponseException;
import it.unisa.tirocinio.services.FabricService;
import jakarta.servlet.http.HttpSession;
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
        List<IdCardItem> assets = fabricService.findAllAssets();
        model.addAttribute("assets", assets);
        return "assetsTemplate";
    }

    @GetMapping("/ownerId")
    public String assetsByOwnerId(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("user");
        if(userId == null ) {
            throw new CustomResponseException(
                    new ResponseEntity<>("no user id found", HttpStatus.BAD_REQUEST));
        }
        List<IdCardItem> items = fabricService.findAssetsByOwnerId(userId);
        model.addAttribute("assets", items);
        return "assetsTemplate";
    }

    @PostMapping(value = "/save-card-item")
    public ResponseEntity<String> card(@ModelAttribute IdCardItem item, HttpSession session) {
        String userId = (String) session.getAttribute("user");
        if(userId == null ) {
            throw new CustomResponseException(
                    new ResponseEntity<>("no user id found", HttpStatus.BAD_REQUEST));
        }
        item.setOwnerId(userId);
        IdCardItem result = fabricService.saveItem(item);
        if (result == null)
            return new ResponseEntity<>("Error saving item", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Saved item: "+result.getId(), HttpStatus.OK);
    }
}
