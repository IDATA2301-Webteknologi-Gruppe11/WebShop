package no.ntnu.ProFlex.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class represent the orders for the users.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@Schema(description = "Represent a order")
@Entity
@Table(name = "Orders")
public class Order {

    @Schema
    @Id
    @GeneratedValue
    private int oid;

    @Schema(description = "The date when the order was placed.")
    @NotNull
    @Column(nullable = false)
    private Date date;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "uid")
    private User uid;

    private static final Logger LOGGER = Logger.getLogger(Order.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    /**
     * Default constructor
     * @param date the date of the order
     */
    public Order(Date date) {
        try {
            this.date = date;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Order() {

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
        if(n < 0) {
            throw new IllegalArgumentException("The integer " + prefiks + " cant be below 0");
        }
        return n;
    }

    /**
     * Setts oid.
     * @param oid the oid that you want to set.
     */
    public void setOid(int oid) {
        try {
            this.oid = integerChecker(oid, "oid");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Return oid.
     * @return oid
     */
    public int getOid() {
        return oid;
    }

    /**
     * Return date
     * @return date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Setts the date
     * @param date the date you want to set.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

    public User getUid() {
        return this.uid;
    }

    public boolean isValid() {
        return oid > 0 && date !=null;
    }

}
