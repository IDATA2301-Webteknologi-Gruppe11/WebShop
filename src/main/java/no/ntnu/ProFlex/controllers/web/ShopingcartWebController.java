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

/**
 * Controller class responsible for handling shopping cart-related requests.
 */
@Controller
public class ShopingcartWebController {

    @Autowired
    AccessUserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    CartItemService cartItemService;

    /**
     * Serves the "ShoppingCart" page, displaying the user's shopping cart and its items.
     *
     * @param model the model object to add attributes for the view
     * @return the name of the Thymeleaf template used to render the HTML view
     */
    @GetMapping("/shoppingcart")
    public String getShoppingCart(Model model) {
        User sessionUser = this.userService.getSessionUser();
        model.addAttribute("sessionUser", sessionUser);
        ShoppingCart shoppingCart = this.shoppingCartService.findByUser(sessionUser);
        model.addAttribute("shoppingCart", shoppingCart);
        List<CartItem> cartItems = this.cartItemService.findAllByShoppingCart(shoppingCart);
        model.addAttribute("cartItems", cartItems);

        if(sessionUser == null) {
            return "no-access";
        }
        return "ShoppingCart";
    }
}
