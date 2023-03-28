package no.ntnu.ProFlex.controllers;

import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.services.OrderService;
import no.ntnu.ProFlex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import no.ntnu.ProFlex.services.ProductService;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Serves the HTML documents with help of Thymeleaf templates.
 * 
 * @author Håvard Hetland Vesbø
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 **/
@Controller
public class PageController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    /**
     * Serve the "Home" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/")
    public String getIndex() {
        return "Index";
    }

    /**
     * Serve the "Profile" page
     *
     * @param model The model where the data will be stored.
     * @param id the id of the user that you want to get the user information.
     * @return Name of the ThymeLeaf template which will be used to render the HTML.
     */
    @GetMapping("/Profile")
    public String getProfile(Model model, @ModelAttribute("id") int id) {
        model.addAttribute("user", this.userService.findById(id));
        model.addAttribute("order", this.orderService.finAllByUid(id));
        return "Profile";
    }

    /**
     * Serve the "Products" page
     *
     * @param model The model where the data will be stored.
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/Products")
    public String getProducts(Model model) {
        model.addAttribute("products", this.productService.getAll());
        return "Products";
    }

    /**
     * Serve the "Product" page
     *
     * @param model The model where the data will be stored.
     * @param id the id of the product.
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/Product/{id}")
    public String getProduct(Model model, @ModelAttribute("id") int id) {
        model.addAttribute("product", this.productService.findById(id));
        return "Product";
    }

    /**
     * Add product.
     *
     * @param product the product that is added to the database
     * @param product the id of the product.
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @PostMapping("/Product/add") //TODO høre om man skal ha slike metoder i page controller
    public String addProduct(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("product", productService.add(product));
        return "Product";
    }

    /**
     * Serve the "ShoppingCart" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/ShoppingCart")
    public String getShoppingCart() {
        return "ShoppingCart";
    }

    /**
    * Serve the "Login" page
    *
    * @return Name of the Thymeleaf template which will be used to render the HTML
    */
    @GetMapping("/Login")
    public String getLogin() {
        return "Login";
    }

    /**
    * Serve the "Register" page
    *
    * @return Name of the Thymeleaf template which will be used to render the HTML
    */
    @GetMapping("/Register")
    public String getRegister() {
        return "Register";
    }
}