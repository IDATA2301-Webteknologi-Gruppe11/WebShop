package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.models.CartItem;
import no.ntnu.ProFlex.models.ShoppingCart;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.AccessUserService;
import no.ntnu.ProFlex.services.CartItemService;
import no.ntnu.ProFlex.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class ShopingcartWebController {

    @Autowired
    AccessUserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CartItemService cartItemService;

    /**
     * Serve the "ShoppingCart" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/shoppingcart")
    public String getShoppingCart(Model model) {
        User sessionUser = this.userService.getSessionUser();
        model.addAttribute("sessionUser", sessionUser);
        ShoppingCart shoppingCart = this.shoppingCartService.findByUid(sessionUser);
        model.addAttribute("shoppingCart",shoppingCart);
        System.out.println(shoppingCart + "      here");
        List<CartItem> cartItems = this.cartItemService.findAllByScid(shoppingCart);
        model.addAttribute("cartItems", cartItems);
        return "ShoppingCart";
    }
}
