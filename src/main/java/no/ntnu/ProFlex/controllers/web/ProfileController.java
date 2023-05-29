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
    @GetMapping("/profile")
    public String getProfile2(Model model) {
        List<Order> orders = this.orderService.findByUser(this.userService.getSessionUser());
        model.addAttribute("orders", orders);
        Iterable<OrderProduct> orderProducts = this.orderProductService.findAll();
        model.addAttribute("orderProducts", orderProducts);
        User sessionUser = this.userService.getSessionUser();
        model.addAttribute("sessionUser",sessionUser);
        return "profile";
    }


    /**
     * Retrieves the admin page for a specific user.
     *
     * @param model    the model object to add attributes for the view
     * @return the name of the view template for the admin page
     */
    @GetMapping("/admin")
    public String getAdmin(Model model) {
        User sessionUser = this.userService.getSessionUser();
        model.addAttribute("sessionUser", sessionUser);
        if(!sessionUser.hasRole("ROLE_ADMIN")) {
            return  "no-access";
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
