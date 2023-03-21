package no.ntnu.ProFlex.controllers;

import no.ntnu.ProFlex.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import no.ntnu.ProFlex.services.ProductService;

/**
 * Serves the HTML documents with help of Thymeleaf templates.
 * 
 * @author Håvard Hetland Vesbø
 * @version 1.0
 **/
@Controller
public class PageController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    /**
     * Serve the "Home" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/")
    public String getIndex(Model model) {
        return "Index";
    }

    /**
     * Serve the "Profile" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/Profile")
    public String getProfile(Model model) {
        return "Profile";
    }

    /**
     * Serve the "Products" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/Products")
    public String getProducts(Model model) {
        return "Products";
    }

    /**
     * Serve the "ShoppingCart" page
     *
     * @return Name of the ThymeLeaf template which will be used to render the HTML
     */
    @GetMapping("/ShoppingCart")
    public String getShoppingCart(Model model) {
        return "ShoppingCart";
    }
}