package no.ntnu.ProFlex.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * Serve the "Login" page
     *
     * @return Name of the Thymeleaf template which will be used to render the HTML
     */
    @GetMapping("/login")
    public String getLogin() {
        return "Loggin";
    }


}
