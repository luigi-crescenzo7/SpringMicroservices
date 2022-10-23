package it.unisa.tirocinio.Main;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(produces = {"application/json"})
@Slf4j
public class SimpleController {

    @Autowired
    private SimpleService service;

    @GetMapping({"/", "/index", "/index.html"})
    public String index(Model model) {
        model.addAttribute("attributo", "Hello");
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }


    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String json() {
        log.info("Call to /json endpoint");
        /*String tmp = service.getJSON();
        model.addAttribute("json_data", tmp);*/
        return service.getJSON();
    }
}
