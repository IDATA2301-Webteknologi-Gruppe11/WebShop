package no.ntnu.ProFlex;

import no.ntnu.ProFlex.controllers.rest.UserController;

public class Main {

    public static void main(String args[]) {
        UserController productController = new UserController();
        System.out.println(productController.getUser());
    }
}
