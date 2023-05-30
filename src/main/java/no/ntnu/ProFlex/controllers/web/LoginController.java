package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * web controller for login page
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@Controller
public class LoginController {

    @Autowired
    AccessUserService userService;

    /**
     * Serve the "Login" page
     *
     * @return Name of the Thymeleaf template which will be used to render the HTML
     */
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("sessionUser", this.userService.getSessionUser());
        return "login";
    }
}
