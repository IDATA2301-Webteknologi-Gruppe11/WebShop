package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.dto.SignupDto;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.AccessUserService;
import no.ntnu.ProFlex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller class responsible for handling user registration and signup-related requests.
 */
@Controller
public class SignupController {

    @Autowired
    UserService userService;

    @Autowired
    AccessUserService accessUserService;

    /**
     * Serves the "Register" page, allowing users to fill out the signup form.
     *
     * @param model the model object to add attributes for the view
     * @return the name of the Thymeleaf template used to render the HTML view
     */
    @GetMapping("/signup-form")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "signup-form";
    }

    /**
     * Creates a new user based on the provided signup data.
     *
     * @param model      the model object to add attributes for the view
     * @param signupDto  the signup data submitted by the user
     * @return the name of the Thymeleaf template used to render the HTML view
     */
    @PostMapping("/signup-form")
    public String createUser(Model model, @ModelAttribute SignupDto signupDto) {
        String errormessage = this.accessUserService.tryCreateNewUser(signupDto.getFirstName(),
                signupDto.getLastName(), signupDto.getEmail(), signupDto.getPass());
        if (errormessage != null) {
            model.addAttribute("user", signupDto);
            model.addAttribute("errorMessage", errormessage);
            return "signup-form";
        }
        return "signup-success";
    }

    /**
     * Serves the "Signup Success" page, indicating that the user registration was successful.
     *
     * @return the name of the Thymeleaf template used to render the HTML view
     */
    @GetMapping("/signup-success")
    public String getSignUpSuccess() {
        return "signup-success";
    }
}
