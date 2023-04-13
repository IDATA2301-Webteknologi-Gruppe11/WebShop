package no.ntnu.ProFlex.controllers;

import jakarta.servlet.http.HttpServletRequest;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.AccessUserService;
import no.ntnu.ProFlex.services.OrderService;
import no.ntnu.ProFlex.services.UserService;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import no.ntnu.ProFlex.services.ProductService;

import java.util.List;

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
    @Autowired
    AccessUserService accessUserService;

    /**
     * Serve the "Home" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/")
    public String getIndex(Model model) {
        //model.addAttribute("featuredProducts", this.productService.getFirst(2)); //Show featured products
        model.addAttribute("featuredProduct", this.productService.getRandomProducts());
        model.addAttribute("valuta", "kr");
        return "index";
    }

    /**
     * Serve the "Profile" page
     *
     * @param model The model where the data will be stored.
     * @param id the id of the user that you want to get the user information.
     * @return Name of the ThymeLeaf template which will be used to render the HTML.
     */
    @GetMapping("/profile")
    public String getProfile() {
        //model.addAttribute("user", this.userService.findById(id));
        //model.addAttribute("order", this.orderService.finAllByUid(id));
        return "Profile";
    }

    /**
     * Serve the "Products" page
     *
     * @param model The model where the data will be stored.
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/products")
    public String getProducts(@RequestParam(required = false) String category, Model model) {
        Iterable<Product> products = null;
        if (category == null) {
            products = this.productService.getAll();
        } else {
            products = this.productService.getByCategory(category);
        }
        model.addAttribute("products", products);
        model.addAttribute("valuta", "kr");
        model.addAttribute("separator", ", ");
        return "Products";
    }

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
        return "product-details";
    }

    /**
     * Add product.
     *
     * @param product the product that is added to the database
     * @param product the id of the product.
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @PostMapping("/products/add") //TODO høre om man skal ha slike metoder i page controller
    public String addProduct(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("product", productService.add(product));
        return "Product";
    }

    /**
     * creates a new user.
     *
     * @param model The model where the data will be stored.
     * @param user the user that you want to create.
     */
    @PostMapping("/register")
    public String createUser(Model model, @ModelAttribute User user) {
       String checkUser = accessUserService.tryCreateNewUser(user.getFirstName(),
               user.getLastName(), user.getEmail(), user.getPass());
       if(checkUser == null) {
           model.addAttribute("user", this.userService.add(user));
       }
       else {
           model.addAttribute("errorMessage", checkUser);
           return "Reister";
       }
        return "index";
    }

    /**
     * Serve the "ShoppingCart" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/shoppingcart")
    public String getShoppingCart() {
        return "ShoppingCart";
    }

    /**
    * Serve the "Login" page
    *
    * @return Name of the Thymeleaf template which will be used to render the HTML
    */
    @GetMapping("/login")
    public String getLogin() {
        return "Loggin";
    }

    /**
    * Serve the "Register" page
    *
    * @return Name of the Thymeleaf template which will be used to render the HTML
    */
    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "Reister";
    }

    @GetMapping("/about")
    public String getAbout() {
        return "About";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "test";
    }
}