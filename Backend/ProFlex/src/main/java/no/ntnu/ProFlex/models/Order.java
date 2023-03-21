package no.ntnu.ProFlex.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @Schema(description = "The user that plaste the order.")
    @NotNull
    @ManyToOne
    @JoinColumn(name ="uid", nullable = false)
    private int uid;

    @Schema(description = "The date when the order was placed.")
    @NotNull
    @Column(nullable = false)
    private Date date;

    @Schema(description = "Many to many relations shit with products.")
    @ManyToMany
    @NotNull
    @Column(nullable = false)
    private Set<Product> products = new HashSet<>();

    private static final Logger LOGGER = Logger.getLogger(Order.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    /**
     * Create an instans of order.
     * @param oid the unique id of the order.
     * @param uid the id of the user that place the order.
     * @param date the when the order was placed.
     */
    public Order(int oid, int uid, Date date) {
        try {
            this.oid = integerChecker(oid, "oid");
            this.uid = integerChecker(uid, "uid");
            this.date = date;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
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
     * Return uid
     * @return uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * Setts the uid.
     * @param uid the uid that you want to set.
     */
    public void setUid(int uid) {
        try {
            this.uid = integerChecker(uid, "uid");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
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

    public boolean isValid() {
        return oid > 0 && date !=null;
    }
}
