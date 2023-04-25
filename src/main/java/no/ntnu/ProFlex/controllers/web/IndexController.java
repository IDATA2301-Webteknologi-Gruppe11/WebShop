package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    ProductService productService;

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
}
