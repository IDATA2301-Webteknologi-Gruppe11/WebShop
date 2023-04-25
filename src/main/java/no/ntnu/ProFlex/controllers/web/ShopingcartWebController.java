package no.ntnu.ProFlex.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopingcartWebController {

    /**
     * Serve the "ShoppingCart" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/shoppingcart")
    public String getShoppingCart(Model model) {
        return "ShoppingCart";
    }
}
