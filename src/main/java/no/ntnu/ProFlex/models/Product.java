package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.*;


/**
 * This class represent product.
 * It is used to create other product fromm it.
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@ApiModel(description = "Represent a product that is added to a product list")
@Entity
@Table(name = "product")
public class Product {

    @ApiModelProperty(value = "The name of the product")
    @NotNull
    @Column(nullable = false, name = "name")
    private String name;

    @ApiModelProperty(value = "A unique integer for the product")
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ApiModelProperty(value = "The price of a product")
    @NotNull
    @Column(nullable = false, name = "price")
    private int price;

    @Column(name = "image")
    @ApiModelProperty(value = "the image path of the product")
    private String image;

    @Column(name = "smallDescription")
    @ApiModelProperty(value = "short description of the product")
    private String shortDescription;

    @Column(name = "newProduct")
    @ApiModelProperty(value = "A bit value that tells if the product is new or not.")
    private boolean newProduct;

    @NotNull
    @Column(nullable = false)
    @ManyToMany(mappedBy = "products")
    @ApiModelProperty(value = "a set of categories for the product")
    private Set<Category> categories = new HashSet<>();

    @Schema(description = "Description of the product")
    @NotNull
    @Column(nullable = false, name = "description", length = 1000)
    @ApiModelProperty(value = "The normal description of the product")
    private String description;

    @OneToMany(mappedBy = "product")
    @JsonBackReference("cartitem-product")
    @ApiModelProperty(value = "List of cart items this product is in.")
    private List<CartItem> cartItems = new ArrayList<>();

    /**
     * Creates a product
     *
     * @param name of the product
     * @param price of the product.
     */
    public Product(String name, int price, String description, Boolean newProduct, String image, String shortDescription) throws IllegalArgumentException {
        this.name = stringChecker(name, "name");
        this.price = integerChecker(price, "price");
        this.description = stringChecker(description, "description");
        this.newProduct = newProduct;
        this.shortDescription = stringChecker(shortDescription, "shortDescription");
        this.image = stringChecker(image, "image");

    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Product() {

    }

    public Set<Category> getCategories(){
        return categories;
    }

    /**
     * Checks a given integer.
     * The number can not be zero or below.
     * If the integer is not valid an Illegal Argument Exception is thrown.
     * If the integer is valid it returns the integer.
     *
     * @param n the integer tha you want to check.
     * @param prefiks the name of the integer.
     * @return the string
     */
    private int integerChecker(int n, String prefiks) {
        if(n < 0) {
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
        if(string == null ||string.trim().isEmpty()) {
            throw new IllegalArgumentException("The string " + prefis + " cant be empty or null.");
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

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the name of a product.
     *
     * @param name the name for the product.
     */
    public void setName(String name) {
        this.name = stringChecker(name, "name");
    }

    /**
     * Sets the ID of a product
     *
     * @param id the ID number for the product.
     */
    public void setId(int id) {
        this.id = integerChecker(id, "id");
    }

    /**
     * Sets the price for the product.
     *
     * @param price a price number for the product.
     */
    public void setPrice(int price) {
        this.price = integerChecker(price, "price");
    }

    public void setDescription(String description) {
        this.description = stringChecker(description, "description");
    }

    /**
     * Update the hole product to a new product.
     *
     * @param product the new product.
     */
    public void setProduct(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.image = product.getImage();
        this.newProduct = product.getNewProduct();
        this.shortDescription = product.getShortDescription();
        this.description = product.getDescription();
    }

    /**
     * Return image patch
     * @return image path
     */
    public String getImage() {
        return this.image;
    }

    /**
     * Setts image path
     * @param path of the image
     */
    public void setImage(String path) {
        this.image = stringChecker(path, "path");
    }

    /**
     * Returns short Description
     * @return short Description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Setts short Description
     * @param shortDescription the new short description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = stringChecker(shortDescription, "shortDescription");
    }

    /**
     * Return if the product is new or not.
     * @return boolean statement, true if product is new, false if not.
     */
    public boolean getNewProduct() {
        return this.newProduct;
    }

    /**
     * Setts boolean statement for if the product is new or not.
     * @param newProduct the boolean statement of the product.
     */
    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    /**
     * Returns list of cart items
     * @return cart items
     */
    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

    /**
     * Setts a list of cart items
     * @param cartItems list of cart items
     */
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Checks if product is valid
     * @return boolean statement true if valid, false if not.
     */
    public boolean isValid() {
        return !" ".equals(this.name) && this.price >= 0 && !" ".equals(this.description)
                && !" ".equals(this.shortDescription) && !" ".equals(this.image);
    }
}
