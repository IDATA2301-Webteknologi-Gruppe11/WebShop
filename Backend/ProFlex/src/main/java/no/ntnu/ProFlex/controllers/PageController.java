package no.ntnu.ProFlex.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Serves the HTML documents with help of Thymeleaf templates. 
 */
@Controller
public class PageController {

    @GetMapping("/")
    public String getHome(Model model) {
        return "Profile";
    }
}