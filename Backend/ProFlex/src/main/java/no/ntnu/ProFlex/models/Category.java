package no.ntnu.ProFlex.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class represent the category of a product.
 * It's an entety class for the web shop database.
 * It will be a table in the database.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@Schema(description = "Represent category, that will be the different categories for the products.")
@Entity
public class Category {

    @Schema(description = "A unique id for the category")
    @Id
    private int cid;

    @Schema(description = "The name of the category.")
    private String cname;

    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    private static final Logger LOGGER = Logger.getLogger(Category.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    public Category() {
    }

    /**
     * Crates and object of category.
     * 
     * @param cid   a unique id for the category.
     * @param cname the name of the category.
     */
    public Category(int cid, String cname) {
        try {
            this.cid = integerChecker(cid, "cid");
            this.cname = stringCheker(cname, "cname");
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
        if (n <= 0) {
            throw new IllegalArgumentException("The number " + prefiks + " can not be zero or below.");
        }
        return n;
    }

    /**
     * Checks if the string is valid.
     * Checks for that the string isn't empty and null.
     * If it is an Illegal Argument Exception is thrown.
     * If it is valid it returns the string.
     * 
     * @param string  that wants to be checked.
     * @param prefiks the name of the string.
     * @return string.
     */
    private String stringCheker(String string, String prefiks) {
        if (string.isEmpty() || string == null) {
            throw new IllegalArgumentException("The String: " + prefiks + " can not be empty or null.");
        }
        return string;
    }

    /**
     * Returns cid.
     * 
     * @return cid.
     */
    public int getCid() {
        return cid;
    }

    /**
     * Returns cname.
     * 
     * @return cname.
     */
    public String getCname() {
        return cname;
    }

    /**
     * Setts the cname for category.
     * 
     * @param cname the cname for category.
     */
    public void setCname(String cname) {
        try {
            this.cname = stringCheker(cname, "cname");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    // TODO id skal kansje være unchangeable, so må dobbelsjekke om den skal ha
    // setCid.
    /**
     * Setts the cid for the category.
     * 
     * @param cid the new cid for category.
     */
    public void setCid(int cid) {
        try {
            this.cid = integerChecker(cid, "cid");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
