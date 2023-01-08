package it.unisa.tirocinio.controllers;

import it.unisa.tirocinio.beans.VaultItem;
import it.unisa.tirocinio.services.CustomResponseException;
import it.unisa.tirocinio.services.VaultItemService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/app/vault")
public class VaultItemController {
    private final VaultItemService vaultItemService;

    public VaultItemController(final VaultItemService service) {
        this.vaultItemService = service;
    }

    @GetMapping("/vaultItem")
    public String itemPage(Model model) {
        model.addAttribute("VaultItem", new VaultItem());
        model.addAttribute("operation", "save");
        return "createVaultItemTemplate";
    }

    @PostMapping("/modifyItem")
    public String modify(@RequestParam Map<String, String> parameters, Model model) {
        parameters.forEach((key, value) -> log.info(key + "  " + value));
        VaultItem item = new VaultItem(parameters.get("id"),
                parameters.get("idCardNumber"),
                parameters.get("resourceURI"),
                parameters.get("ownerId"),
                parameters.get("itemName"),
                LocalDate.parse(parameters.get("creationDate"), DateTimeFormatter.ISO_LOCAL_DATE));

        model.addAttribute("VaultItem", item);
        model.addAttribute("operation", "update");
        return "createVaultItemTemplate";
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute VaultItem item, HttpSession session) {
        String uId = (String) session.getAttribute("user");

        if (uId == null)
            throw new CustomResponseException(
                    new ResponseEntity<>("user id not found in http session", HttpStatus.BAD_REQUEST));

        item.setCreationDate(LocalDate.now());
        boolean flag = vaultItemService.updateItem(item);
        if (!flag)
            throw new CustomResponseException(
                    new ResponseEntity<>("item not updated", HttpStatus.BAD_REQUEST));

        return itemsByOwnerId(null, session);
    }

    @PostMapping("/delete")
    @CrossOrigin(value = "*")
    public ResponseEntity<String> delete(@RequestBody String body) {
        VaultItem item = new VaultItem();
        item.setId(body);
        boolean flag = vaultItemService.deleteItem(item);
        if (!flag) return new ResponseEntity<>("item not deleted", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("item deleted", HttpStatus.OK);
    }

    @PostMapping("/save")
    public ModelAndView item(@ModelAttribute VaultItem item, HttpSession session) {
        String uId = (String) session.getAttribute("user");

        if (uId == null)
            throw new CustomResponseException(
                    new ResponseEntity<>("user id not found in http session", HttpStatus.BAD_REQUEST));

        item.setOwnerId(uId);
        LocalDate nowDate = LocalDate.now();
        item.setCreationDate(nowDate);

        boolean flag = vaultItemService.saveItem(item);
        if (!flag)
            throw new CustomResponseException(new ResponseEntity<>("", HttpStatus.BAD_REQUEST));

        return itemsByOwnerId(null, session);
    }

    @GetMapping(value = "/user-items")
    public ModelAndView itemsByOwnerId(Model model, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/show-items");
        String userId = (String) session.getAttribute("user");
        if (userId == null)
            throw new CustomResponseException(
                    new ResponseEntity<>("no user id found in session", HttpStatus.BAD_REQUEST));
        List<VaultItem> fetchedItems = vaultItemService.findAllById(userId);
        modelAndView.addObject("items", fetchedItems);
        return modelAndView;
    }

    @GetMapping(value = "/all-items")
    public String items(Model model) {
        List<VaultItem> items = vaultItemService.findAll();
        model.addAttribute("items", items);
        return "show-items";
    }
}
