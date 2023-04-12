package no.ntnu.ProFlex;

import no.ntnu.ProFlex.controllers.ProductController;
import no.ntnu.ProFlex.controllers.UserController;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.repository.ProductRepository;
import no.ntnu.ProFlex.services.ProductService;
import no.ntnu.ProFlex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class Main {

    public static void main(String args[]) {
        UserController productController = new UserController();
        System.out.println(productController.getUser());
    }
}
