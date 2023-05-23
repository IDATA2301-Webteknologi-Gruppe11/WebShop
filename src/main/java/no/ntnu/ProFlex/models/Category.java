package no.ntnu.ProFlex.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "category")
public class Category {

    @Schema(description = "A unique id for the category")
    @Id
    @GeneratedValue
    private int cid;

    @Schema(description = "The name of the category.")
    @NotNull
    @Column(nullable = false)
    private String cname;

    @ManyToMany
    @NotNull
    @Column(nullable = false)
    @JoinTable(name = "product_categories",
    joinColumns = @JoinColumn(name = "cid"),
    inverseJoinColumns = @JoinColumn(name = "pid")
    )
    private Set<Product> products = new HashSet<>();

    /**
     * Crates and object of category.
     * @param cname the name of the category.
     */
    public Category(String cname) {
        this.cname = stringCheker(cname, "cname");
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Category() {

    }

    /**
     * Checks if the string is valid.
     * Checks for that the string isn't empty and null.
     * If it is an Illegal Argument Exception is thrown.
     * If it is valid it returns the string.
     * @param string that wants to be checked.
     * @param prefiks the name of the string.
     * @return string.
     */
    private String stringCheker(String string, String prefiks) {
        if( string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException("The string " + prefiks + " cant be empty or null.");
        }
        return string;
    }

    /**
     * Returns cid.
     * @return cid.
     */
    public int getCid() {
        return cid;
    }

    /**
     * Returns cname.
     * @return cname.
     */
    public String getCname() {
        return cname;
    }

    /**
     * Setts the cname for category.
     * @param cname the cname for category.
     */
    public void setCname(String cname) {
        this.cname = stringCheker(cname, "cname");
    }

    //TODO id skal kansje være unchangeable, so må dobbelsjekke om den skal ha setCid.
    /**
     * Setts the cid for the category.
     * @param cid the new cid for category.
     */
    public void setCid(int cid) {
        this.cid = cid;
    }

    /**
     * Tests if the category is valid
     * @return
     */
    public boolean isValid() {
        return !" ".equals(this.cname);
    }

}
