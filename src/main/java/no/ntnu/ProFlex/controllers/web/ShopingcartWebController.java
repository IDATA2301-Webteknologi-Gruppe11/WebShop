package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.services.AccessUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopingcartWebController {

    @Autowired
    AccessUserService userService;
    /**
     * Serve the "ShoppingCart" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/shoppingcart")
    public String getShoppingCart(Model model) {
        model.addAttribute("sessionUser", this.userService.getSessionUser());
        return "ShoppingCart";
    }
}
