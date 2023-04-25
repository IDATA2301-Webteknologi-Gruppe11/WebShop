package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Controller
public class SearchController {

    @Autowired
    ProductService productService;

    @GetMapping("/search")
    public ModelAndView searchProducts(@RequestParam("query") String query) {
        List<Product> products = this.productService.searchProducts(query);
        ModelAndView modelAndView = new ModelAndView("searchResults");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
