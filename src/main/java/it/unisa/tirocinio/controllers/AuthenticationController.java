package it.unisa.tirocinio.controllers;


import it.unisa.tirocinio.beans.User;
import it.unisa.tirocinio.services.CustomResponseException;
import it.unisa.tirocinio.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


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
                              @RequestParam(name = "password") String password, HttpSession session) {

        ModelAndView mav = new ModelAndView("show-items");
        String userId = userService.login(email, password);
        boolean flag = userId != null;
        if (flag) {
            session.setAttribute("user", userId);
        }

        return vaultItemController.itemsByOwnerId(mav, session);
    }
}
