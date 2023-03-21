package no.ntnu.ProFlex.controllers;

import no.ntnu.ProFlex.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import no.ntnu.ProFlex.services.ProductService;

/*
 * Serves the HTML documents with help of Thymeleaf templates. 
 */
@Controller
public class PageController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    
    @GetMapping("/")
    public String getIndex(Model model) {
        return "Index";
    }

    @GetMapping("/Profile")
    public String getProfile(Model model) {
        return "Profile";
    }

    @GetMapping("/Products")
    public String getProducts(Model model) {
        return "Products";
    }

    @GetMapping("/ShoppingCart")
    public String getShoppingCart(Model model) {
        return "ShoppingCart";
    }
}