package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.AccessUserService;
import no.ntnu.ProFlex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    AccessUserService accessUserService;

    /**
     * Serve the "Register" page
     *
     * @return Name of the Thymeleaf template which will be used to render the HTML
     */
    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "Reister";
    }

    /**
     * creates a new user.
     *
     * @param model The model where the data will be stored.
     * @param user the user that you want to create.
     */
    @PostMapping("/register")
    public String createUser(Model model, @ModelAttribute User user) {
        String checkUser = this.accessUserService.tryCreateNewUser(user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getPass());
        if(checkUser == null) {
            model.addAttribute("user", this.userService.add(user));
        }
        else {
            model.addAttribute("errorMessage", checkUser);
            return "Reister";
        }
        return "index";
    }
}
