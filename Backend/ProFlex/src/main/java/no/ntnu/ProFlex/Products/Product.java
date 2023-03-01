package no.ntnu.ProFlex.Products;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import no.ntnu.ProFlex.entities.Category;
import nonapi.io.github.classgraph.json.Id;

import java.util.HashSet;
import java.util.Set;


/**
 * This class represent product.
 * It is used to create other product fromm it.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "Represent a product that is added to a product list", title = "A product")
@Entity
@Table(name = "product")
public class Product {

    @Schema(description = "The name of the product")
    private String name;

    @Schema(description = "A unique integer for the product")
    @Id
    @GeneratedValue
    private int id;

    @Schema(description = "The price of a product")
    private int price;

    @Schema(description = "The description of a product, use for what category the product belong to")
    private String description;

    @ManyToMany
    private Set<Category> categories = new HashSet<>();


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

    /**
     * Update the hole product to a new product.
     * @param product the new product.
     */
    public void setProduct(Product product) {
        this.name = product.getName();
        this.id = product.getId();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }

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
