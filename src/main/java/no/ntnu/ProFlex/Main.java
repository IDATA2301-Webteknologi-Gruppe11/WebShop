package no.ntnu.ProFlex;

import no.ntnu.ProFlex.controllers.rest.UserController;

public class Main {

    public static void main(String args[]) {
        UserController userController = new UserController();
        System.out.println(userController.getUser());
    }
}
