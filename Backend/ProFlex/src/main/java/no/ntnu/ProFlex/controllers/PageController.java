package no.ntnu.ProFlex.Controllers;

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
    public String getHome(Model model) {
        return "Profile.html";
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        return "Index";
    }
}