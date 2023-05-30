package no.ntnu.ProFlex.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a category of a product.
 * It is an entity class for the web shop database.
 * It will be a table in the database.
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@ApiModel(description = "Represents a category that can be assigned to products.")
@Entity
@Table(name = "category")
public class Category {

    @ApiModelProperty(value = "A unique ID for the category.")
    @Id
    @GeneratedValue
    private int id;

    @ApiModelProperty(value = "The name of the category.")
    @NotNull
    @Column(nullable = false)
    private String name;

    @ManyToMany
    @NotNull
    @Column(nullable = false)
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "category"),
            inverseJoinColumns = @JoinColumn(name = "product")
    )
    @ApiModelProperty(value = "A set of products")
    private Set<Product> products = new HashSet<>();

    /**
     * Creates a new Category object with the specified name.
     *
     * @param name the name of the category
     */
    public Category(String name) {
        this.name = stringChecker(name, "name");
    }

    /**
     * Empty constructor that is needed for JPA.
     */
    public Category() {

    }

    /**
     * Checks if the string is valid.
     * It checks that the string is not empty or null.
     * If it is empty or null, an IllegalArgumentException is thrown.
     * If it is valid, the string is returned.
     *
     * @param string  the string to check
     * @param prefix  the name of the string
     * @return the string if it is valid
     * @throws IllegalArgumentException if the string is empty or null
     */
    private String stringChecker(String string, String prefix) {
        if (string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException("The string " + prefix + " cannot be empty or null.");
        }
        return string;
    }

    /**
     * Retrieves the ID of the category.
     *
     * @return the ID of the category
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the name of the category.
     *
     * @return the name of the category
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for the category.
     *
     * @param name the name for the category
     */
    public void setName(String name) {
        this.name = stringChecker(name, "name");
    }

    /**
     * Sets the ID for the category.
     *
     * @param id the new ID for the category
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Checks if the category is valid.
     *
     * @return true if the category is valid, false otherwise
     */
    public boolean isValid() {
        return !"".equals(this.name.trim());
    }
}