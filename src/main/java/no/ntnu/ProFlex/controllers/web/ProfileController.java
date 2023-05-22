package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ProfileController {

    @Autowired
    private AccessUserService userService;

    /**
     * Serve the "Profile" page
     *
     * @param model The model where the data will be stored.
     * @param id the id of the user that you want to get the user information.
     * @return Name of the ThymeLeaf template which will be used to render the HTML.
     */
    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("sessionUser", this.userService.getSessionUser());
        return "Profile";
    }

//    @GetMapping("user")
//    public String userPage(Model model) {
//        System.out.println("user is: " + userService.getSessionUser().getEmail());
//        model.addAttribute("user", this.userService.getSessionUser());
//        return "testUser";
//    }

    @GetMapping("/no-access")
    public String getNoAccess() {
        return "no-access";
    }

    @GetMapping("/profile2")
    public String getProfile2() {
        return "profile2";
    }
}
