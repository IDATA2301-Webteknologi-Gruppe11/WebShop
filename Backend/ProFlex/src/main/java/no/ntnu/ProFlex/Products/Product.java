package no.ntnu.ProFlex.Products;

import no.ntnu.ProFlex.Controllers.ProFlexController;

/**
 * This class represent product.
 * It is used to create other product fromm it.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
public abstract class Product {

    private String name;
    private int id;
    private int price;
    //TODO check what to do with description. How tho make it work with categories
    private String description;


    /**
     * Creates a product
     * @param name of the product
     * @param id unique identifier for the product. Can't have the same id as any other products.
     * @param price of the product.
     * @param description describe the product/what categories it belongs to.
     */
    protected Product(String name, int id, int price, String description) throws IllegalArgumentException {

        //Checks name is valid.
        if(name.isEmpty()) {
            throw new IllegalArgumentException("Invalid Value.");
        }
        else {
            this.name = name;
        }

        //Checks id is valid.
        if(id < 0 ) {
            throw new IllegalArgumentException("Invalid value");
        }
        else {
            this.id = id;
        }

        //Checks price is valid.
        if(price < 0) {
            throw new IllegalArgumentException("Invalid value");
        }
        else {
            this.price = price;
        }

        //Checks description is valid.
        if(description.isEmpty()) {
            throw new IllegalArgumentException("Invalid Value");
        }
        else {
            this.description = description;
        }
    }

    /**
     * Returns the name of the product.
     * @return ID.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the ID of the products
     * @return ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the price of the products
     * @return prices.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Returns the description of the product
     * @return description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the name of a product.
     * @param name the name for the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the ID of a product
     * @param id the ID number for the product.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the price for the product.
     * @param price a price number for the product.
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Sets the description of product.
     * @param description explains the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

//    /**
//     * Checks if the ID already exists
//     * @param id the id to check
//     * @return ture or false depending on if the id exist or not. True if exists and false if not.
//     */
//    private boolean checkhIfIdIsInProductList(int id) {
//        boolean idFound = false;
//        for(int i = 0; i < proFlexController.getProductList().getAllIdFromProductList().size(); i++) {
//            if(id == proFlexController.getProductList().getAllIdFromProductList().get(i)) {
//                idFound = true;
//            }
//        }
//        return idFound;
//    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
