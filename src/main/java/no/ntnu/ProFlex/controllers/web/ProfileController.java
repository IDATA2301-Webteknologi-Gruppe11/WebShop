package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.models.OrderProduct;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.AccessUserService;
import no.ntnu.ProFlex.services.OrderProductService;
import no.ntnu.ProFlex.services.OrderService;
import no.ntnu.ProFlex.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Controller class for handling profile-related web requests.
 * This class manages the profile page for users and the admin page for administrators.
 */
@Controller
public class ProfileController {

    @Autowired
    private AccessUserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderProductService orderProductService;

    @Autowired
    CategoryService categoryService;

    /**
     * Retrieves the profile page for a specific user.
     *
     * @param model    the model object to add attributes for the view
     * @param username the username of the user whose profile page is requested
     * @return the name of the view template for the profile page
     */
    @GetMapping("/profile/{username}")
    public String getProfile2(Model model, @PathVariable String username) {
        List<Order> orders = this.orderService.findByUser(this.userService.getSessionUser());
        model.addAttribute("orders", orders);
        Iterable<OrderProduct> orderProducts = this.orderProductService.findAll();
        model.addAttribute("orderProducts", orderProducts);
        return handleProfilePageRequest(username, model);
    }

    /**
     * Handles the profile page request by validating the authenticated user and adding attributes to the model.
     *
     * @param username the username of the user whose profile page is requested
     * @param model    the model object to add attributes for the view
     * @return the name of the view template for the profile page if the user is authenticated and has access, or "no-access" otherwise
     */
    private String handleProfilePageRequest(String username, Model model) {
        User authenticatedUser = this.userService.getSessionUser();
        if (authenticatedUser == null) {
            return "no-access";
        }
        if (authenticatedUser.getEmail().equals(username)) {
            model.addAttribute("sessionUser", authenticatedUser);
        }
        return "profile";
    }

    /**
     * Retrieves the admin page for a specific user.
     *
     * @param model    the model object to add attributes for the view
     * @param username the username of the user whose admin page is requested
     * @return the name of the view template for the admin page
     */
    @GetMapping("/admin/{username}")
    public String getAdmin(Model model, @PathVariable String username) {
        Iterable<Category> categories = this.categoryService.getAll();
        model.addAttribute("categories", categories);
        return handleAdminPageRequest(username, model);
    }

    /**
     * Handles the admin page request by validating the authenticated user and adding attributes to the model.
     *
     * @param username the username of the user whose admin page is requested
     * @param model    the model object to add attributes for the view
     * @return the name of the view template for the admin page if the user is authenticated and has admin role, or "no-access" otherwise
     */
    private String handleAdminPageRequest(String username, Model model) {
        User authenticatedUser = this.userService.getSessionUser();
        if (authenticatedUser == null) {
            return "no-access";
        }
        if (authenticatedUser.getEmail().equals(username) && authenticatedUser.hasRole("ADMIN")) {
            model.addAttribute("sessionUser", authenticatedUser);
        }
        return "admin";
    }

    /**
     * Retrieves the "no-access" page.
     *
     * @return the name of the view template for the "no-access" page
     */
    @GetMapping("/no-access")
    public String getNoAccess() {
        return "no-access";
    }
}
