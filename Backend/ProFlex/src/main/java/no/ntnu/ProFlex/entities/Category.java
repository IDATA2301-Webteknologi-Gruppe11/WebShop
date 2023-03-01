package no.ntnu.ProFlex.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.logging.Logger;

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
    @Column(nullable = false) //TODO trenger eg å ha kode som som sjekker om det er null eller ikkje når man bruker notasjoner. Høre med girtz.
    @NotNull
    private String cname;

    private static final Logger LOGGER = Logger.getLogger(Category.class.getName());
    private static final String EXCEPTION_WARNING_MASSAGE = "Caught and Illegal Argument Exception: ";

    /**
     * Crates and object of category.
     * @param cid a unique id for the category.
     * @param cname the name of the category.
     */
    public Category(int cid, String cname) {
        try {
            this.cid = integerChecker(cid, "cid");
            this.cname = stringCheker(cname, "cname");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(EXCEPTION_WARNING_MASSAGE + illegalArgumentException.getMessage());
        }
    }

    /**
     * Checks a given integer.
     * The number can not be zero or below.
     * If the integer is not valid an Illegal Argument Exception is thrown.
     * If the integer is valid it returns the integer.
     * @param n the integer tha you want to check.
     * @param prefiks the name of the integer.
     * @return the string
     */
    private int integerChecker(int n, String prefiks) {
        if(n <= 0) {
            throw new IllegalArgumentException("The number " + prefiks + " can not be zero or below.");
        }
        return n;
    }

    /**
     * Checks a given string.
     * The string can not be empty or null.
     * If the string is not valid an Illegal Argument Exception is thrown.
     * If the string is valid it returns the string.
     * @param string the string that you want to check.
     * @param prefiks the name of the string.
     * @return the string
     */
    private String stringCheker(String string, String prefiks) {
        if(string.isEmpty() || string == null) {
            throw new IllegalArgumentException("The String: " + prefiks + " can not be empty or null.");
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
        try {
            this.cname = stringCheker(cname, "cname");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(EXCEPTION_WARNING_MASSAGE + illegalArgumentException.getMessage());
        }
    }

    //TODO id skal kansje være unchangeable, so må dobbelsjekke om den skal ha setCid.
    /**
     * Setts the cid for the category.
     * @param cid the new cid for category.
     */
    public void setCid(int cid) {
        try {
            this.cid = integerChecker(cid, "cid");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(EXCEPTION_WARNING_MASSAGE + illegalArgumentException.getMessage());
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
