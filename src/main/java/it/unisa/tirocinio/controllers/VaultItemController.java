package it.unisa.tirocinio.controllers;

import it.unisa.tirocinio.beans.VaultItem;
import it.unisa.tirocinio.services.CustomResponseException;
import it.unisa.tirocinio.services.VaultItemService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/app/vault")
public class VaultItemController {
    private final Logger logger = LoggerFactory.getLogger(VaultItemController.class);

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

    @PostMapping("/item-page")
    public String modify(@ModelAttribute VaultItem item, Model model) {
        model.addAttribute("VaultItem", item);
        model.addAttribute("operation", "updateItem");
        return "createVaultItemTemplate";
    }

    @PostMapping("/updateItem")
    public ModelAndView update(@ModelAttribute VaultItem item, HttpSession session) {
        String uId = (String) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("show-items");

        if (uId == null)
            throw new CustomResponseException(
                    new ResponseEntity<>("user id not found in http session", HttpStatus.BAD_REQUEST));

        item.setCreationDate(LocalDate.now());
        item.setOwnerId(uId);

        boolean flag = vaultItemService.updateItem(item);

        if (!flag)
            throw new CustomResponseException(
                    new ResponseEntity<>("item not updated", HttpStatus.BAD_REQUEST));

        return itemsByOwnerId(modelAndView, session);
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
        ModelAndView modelAndView = new ModelAndView("show-items");

        if (uId == null)
            throw new CustomResponseException(
                    new ResponseEntity<>("user id not found in http session", HttpStatus.BAD_REQUEST));

        item.setOwnerId(uId);
        LocalDate nowDate = LocalDate.now();
        item.setCreationDate(nowDate);

        boolean flag = vaultItemService.saveItem(item);
        if (!flag)
            throw new CustomResponseException(new ResponseEntity<>("item not saved", HttpStatus.BAD_REQUEST));

        return itemsByOwnerId(modelAndView, session);
    }

    @GetMapping(value = "/user-items")
    public ModelAndView itemsByOwnerId(ModelAndView modelAndView, HttpSession session) {
        String userId = (String) session.getAttribute("user");
        if (userId == null)
            throw new CustomResponseException(
                    new ResponseEntity<>("no user id found in session", HttpStatus.BAD_REQUEST));
        List<VaultItem> fetchedItems = vaultItemService.findAllById(userId);
        modelAndView.addObject("items", fetchedItems);
        return modelAndView;
    }
}
