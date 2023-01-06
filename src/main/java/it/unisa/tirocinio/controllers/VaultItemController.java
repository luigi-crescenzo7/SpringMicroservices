package it.unisa.tirocinio.controllers;

import it.unisa.tirocinio.beans.VaultItem;
import it.unisa.tirocinio.services.VaultItemService;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/app/vaultItem")
@Slf4j
public class VaultItemController {
    private final VaultItemService vaultItemService;

    public VaultItemController(final VaultItemService service) {
        this.vaultItemService = service;
    }

    @GetMapping("/vaultItem")
    public String itemPage(Model model) {
        model.addAttribute("VaultItem", new VaultItem());
        return "createVaultItemTemplate";
    }

    @PostMapping("/save-vault-item")
    public ResponseEntity<String> item(@ModelAttribute VaultItem item, BindingResult result, HttpSession session) {
        if (result.hasErrors())
            return new ResponseEntity<>("Error mapping VaultItem object", HttpStatus.BAD_REQUEST);
        String uId = (String) session.getAttribute("user");

        if (uId == null)
            return new ResponseEntity<>("user id not found in http session", HttpStatus.BAD_REQUEST);

        item.setOwnerId(uId);

        boolean flag = vaultItemService.saveItem(item);
        if (!flag) return new ResponseEntity<>("item not saved", HttpStatus.BAD_REQUEST);

        log.info("Item received: " + item);
        return new ResponseEntity<>("item saved", HttpStatus.OK);
    }

    @GetMapping(value = "/find-by-id")
    public String itemsByOwnerId(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("user");
        List<VaultItem> fetchedItems = vaultItemService.findAllById(userId);
        model.addAttribute("items", fetchedItems);
        return "show-items";
    }

    @GetMapping(value = "/vault-items")
    public ResponseEntity<List<VaultItem>> items() {
        List<VaultItem> items = vaultItemService.findAll();
        return new ResponseEntity<>(Objects.requireNonNullElseGet(items, ArrayList::new), HttpStatus.OK);
    }
}
