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
/**
 * Controller class responsible for handling search-related requests.
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@Controller
public class SearchController {

    @Autowired
    ProductService productService;

    @Autowired
    AccessUserService userService;

    /**
     * Handles the search products request and returns the search results.
     *
     * @param query the search query
     * @param model the model object to add attributes for the view
     * @return a ModelAndView object representing the search results view with the matching products
     */
    @GetMapping("/search")
    public ModelAndView searchProducts(@RequestParam("query") String query, Model model) {
        model.addAttribute("sessionUser", this.userService.getSessionUser());
        List<Product> products = this.productService.searchProducts(query);
        ModelAndView modelAndView = new ModelAndView("searchResults");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
