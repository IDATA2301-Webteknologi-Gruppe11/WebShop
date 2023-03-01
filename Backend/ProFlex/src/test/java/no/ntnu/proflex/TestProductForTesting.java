package no.ntnu.ProFlex;

import no.ntnu.ProFlex.models.Product;

public class TestProductForTesting extends Product {

    /**
     * Creates a product
     *
     * @param name        of the product
     * @param id          unique identifier for the product. Can't have the same id as any other products.
     * @param price       of the product.
     * @param description describe the product/what categories it belongs to.
     */
    protected TestProductForTesting(String name, int id, int price, String description) throws IllegalArgumentException {
        super(name, id, price, description);
    }
}
