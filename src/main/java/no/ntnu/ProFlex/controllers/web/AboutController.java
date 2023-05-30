package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * web controller for about page
 *
 * @author IDATA2306 Group 11
 */
@Controller
public class AboutController {
    @Autowired
    AccessUserService userService;

    /**
     * Retrieves the "About" page.
     *
     * @param model the model object to add attributes for the view
     * @return the name of the view template for the "About" page
     */
    @GetMapping("/about")
    public String getAbout(Model model) {
        model.addAttribute("sessionUser", this.userService.getSessionUser());
        return "about";
    }
}
