package no.ntnu.ProFlex.controllers.web;

import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.services.AccessUserService;
import no.ntnu.ProFlex.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Controller
public class SearchController {

    @Autowired
    ProductService productService;

    @Autowired
    AccessUserService userService;

    @GetMapping("/search")
    public ModelAndView searchProducts(@RequestParam("query") String query, Model model) {
        model.addAttribute("sessionUser", this.userService.getSessionUser());
        List<Product> products = this.productService.searchProducts(query);
        ModelAndView modelAndView = new ModelAndView("searchResults");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
