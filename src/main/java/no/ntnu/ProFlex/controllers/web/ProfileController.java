package no.ntnu.ProFlex.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    /**
     * Serve the "Profile" page
     *
     * @param model The model where the data will be stored.
     * @param id the id of the user that you want to get the user information.
     * @return Name of the ThymeLeaf template which will be used to render the HTML.
     */
    @GetMapping("/profile")
    public String getProfile() {
        //model.addAttribute("user", this.userService.findById(id));
        //model.addAttribute("order", this.orderService.finAllByUid(id));
        return "Profile";
    }
}
