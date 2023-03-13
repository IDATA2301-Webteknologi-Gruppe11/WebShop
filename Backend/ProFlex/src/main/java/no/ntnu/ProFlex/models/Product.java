package no.ntnu.ProFlex.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class represent product.
 * It is used to create other product fromm it.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "Represent a product that is added to a product list", title = "A product")
@Entity
public class Product {

    @ManyToMany
    @JoinTable(name = "category_product", inverseJoinColumns = @JoinColumn(name = "cid"), joinColumns = @JoinColumn(name = "id"))
    private Set<Category> categories = new HashSet<>();

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Schema(description = "The name of the product")
    private String name;

    @Schema(description = "A unique integer for the product")
    @Id
    private int id;

    @Schema(description = "The price of the product")
    private int price;

    private static final Logger LOGGER = Logger.getLogger(Product.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    public Product() {
    }

    /**
     * Creates a product
     *
     * @param name  of the product
     * @param id    unique identifier for the product. Can't have the same id as any
     *              other products.
     * @param price of the product.
     */
    public Product(String name, int id, int price) throws IllegalArgumentException {
        try {
            this.name = stringChecker(name, "name");
            this.id = integerChecker(id, "id");
            this.price = integerChecker(price, "price");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Checks a given integer.
     * The number can not be zero or below.
     * If the integer is not valid an Illegal Argument Exception is thrown.
     * If the integer is valid it returns the integer.
     *
     * @param n       the integer tha you want to check.
     * @param prefiks the name of the integer.
     * @return the string
     */
    private int integerChecker(int n, String prefiks) {
        if (n < 0) {
            throw new IllegalArgumentException("The integer " + prefiks + " cant be below 0");
        }
        return n;
    }

    /**
     * Checks if the string is valid.
     * Checks for that the string isn't empty and null.
     * If it is an Illegal Argument Exception is thrown.
     * If it is valid it returns the string.
     *
     * @param string that wants to be checked.
     * @param prefis the name of the string.
     * @return string.
     */
    private String stringChecker(String string, String prefis) {
        if (string.isEmpty() || string == null) {
            throw new IllegalArgumentException("The String: " + prefis + " can not be empty or null.");
        }
        return string;
    }

    /**
     * Returns the name of the product.
     *
     * @return ID.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the ID of the products
     *
     * @return ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the price of the products
     *
     * @return prices.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Sets the name of a product.
     *
     * @param name the name for the product.
     */
    public void setName(String name) {
        try {
            this.name = stringChecker(name, "name");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Sets the ID of a product
     *
     * @param id the ID number for the product.
     */
    public void setId(int id) {
        try {
            this.id = integerChecker(id, "id");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Sets the price for the product.
     *
     * @param price a price number for the product.
     */
    public void setPrice(int price) {
        try {
            this.price = integerChecker(price, "price");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Update the hole product to a new product.
     * 
     * @param product the new product.
     */
    public void setProduct(Product product) {
        this.name = product.getName();
        this.id = product.getId();
        this.price = product.getPrice();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                '}';
    }
}
