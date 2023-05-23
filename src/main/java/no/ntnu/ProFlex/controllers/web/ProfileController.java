package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.models.OrderProduct;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.AccessUserService;
import no.ntnu.ProFlex.services.OrderProductService;
import no.ntnu.ProFlex.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private AccessUserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderProductService orderProductService;

    @GetMapping("/profile/{username}")
    public String getProfile2(Model model, @PathVariable String username) {
        List<Order> orders = this.orderService.findAllByUid(this.userService.getSessionUser());
        model.addAttribute("orders", orders);
        Iterable<OrderProduct> orderProducts = this.orderProductService.findAll();
        model.addAttribute("orderProducts", orderProducts);
       return handleProfilePageRequest(username, model);
    }

    private String handleProfilePageRequest(String username, Model model) {
        User autheticatedUser = this.userService.getSessionUser();
        if(autheticatedUser == null){
            return "no-access";
        }
        if(autheticatedUser.getEmail().equals(username)) {
            model.addAttribute("sessionUser", autheticatedUser);
        }
        return "admin";
    }

    @GetMapping("/admin/{username}")
    public String getAdmin(Model model, @PathVariable String username) {
        return   handleAdminPageRequest(username, model);
    }

    private String handleAdminPageRequest(String username, Model model) {
        User autheticatedUser = this.userService.getSessionUser();
        if(autheticatedUser == null) {
            return "no-access";
        }
        if(autheticatedUser.getEmail().equals(username) && autheticatedUser.hasRole("ADMIN")) {
            model.addAttribute("sessionUser", autheticatedUser);
        }
        return "admin";
    }

    @GetMapping("/no-access")
    public String getNoAccess() {
        return "no-access";
    }

    @GetMapping("change-user-information")
    public String getChangeUserInformation(Model model) {
        User sessionUser = this.userService.getSessionUser();
        model.addAttribute("sessionUser", sessionUser);
        return "change-user-information";
    }
}
