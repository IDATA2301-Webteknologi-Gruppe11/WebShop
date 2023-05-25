package no.ntnu.ProFlex.controllers.web;

import com.sun.jdi.VMOutOfMemoryException;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.models.ShoppingCart;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.AccessUserService;
import no.ntnu.ProFlex.services.ProductService;
import no.ntnu.ProFlex.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductWebController {

    @Autowired
    ProductService productService;

    @Autowired
    AccessUserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * Serve the "Product" page
     *
     * @param model The model where the data will be stored.
     * @param id the id of the product.
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/products/{id}")
    public String getProduct(Model model, @ModelAttribute("id") int id) {
        model.addAttribute("products", this.productService.findById(id));
        User sessionUser = this.userService.getSessionUser();
        model.addAttribute("sessionUser", sessionUser);
        ShoppingCart shoppingCart = this.shoppingCartService.findByUid(sessionUser);
        model.addAttribute("shoppingCart", shoppingCart);
        return "product-details";
    }

    /**
     * Serve the "Products" page
     *
     * @param model The model where the data will be stored.
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/products")
    public String getProducts(@RequestParam(required = false) String category, Model model) {
        model.addAttribute("sessionUser", this.userService.getSessionUser());
        return "Products";
    }




    //        Iterable<Product> products = null;
//        if (category == null) {
//            products = this.productService.getAll();
//        } else {
//            products = this.productService.getByCategory(category);
//        }
//        model.addAttribute("products", products);
//        model.addAttribute("valuta", "kr");
//        model.addAttribute("separator", ", ");
}
