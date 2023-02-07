package no.ntnu.ProFlex;

import no.ntnu.ProFlex.Products.OnlineSchedulingSoftwareProduct;
import no.ntnu.ProFlex.Products.Product;
import no.ntnu.ProFlex.Products.ProductList;
import no.ntnu.ProFlex.Products.ProflexBPASolutionsProduct;

public class Main {
    public static void main(String args[]) {
//        Product product = new OnlineSchedulingSoftwareProduct();
//        Product product1 = new ProflexBPASolutionsProduct();
//
//        System.out.println(product.getId());
//        System.out.println(product.getName());
//        System.out.println(product.getPrice());
//        System.out.println(product.getDescription());
//        System.out.println("-------------");
//        System.out.println(product1.getId());
//        System.out.println(product1.getName());
//        System.out.println(product1.getPrice());
//        System.out.println(product1.getDescription());

        ProductList productList = new ProductList();
        System.out.println(productList.getProductFromAGivenID(4));
    }
}
