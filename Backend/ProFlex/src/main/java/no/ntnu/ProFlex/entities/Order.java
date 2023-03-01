package no.ntnu.ProFlex.entities;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jdk.jfr.Description;
import jdk.jfr.Enabled;

import javax.swing.*;
import java.util.Date;
import java.util.logging.Logger;

/**
 * This class represent the orders for the users.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@Schema(description = "Represent a order")
@Entity
@Table(name = "Order")
public class Order {

    @Schema
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    @Schema(description = "The user that plaste the order.") //TODO leg til som foren key.
    private User user;

    @Schema(description = "The date when the order was plaste")
    private Date date;

    private static final Logger LOGGER = Logger.getLogger(Order.class.getName());
    private static final String EXCEPTION_WARNING_MASSAGE = "Caught and Illegal Argument Exception: ";

    /**
     * Create an instans of order.
     * @param oid the unique id of the order.
     * @param user the id of the user that place the order.
     * @param date the when the order was placed.
     */
    public Order(int oid, User user, Date date) {
        try {
            this.oid = integerChecker(oid, "oid");
            this.user = user;
            this.date = date;
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(EXCEPTION_WARNING_MASSAGE + illegalArgumentException.getMessage());
        }
    }

    /**
     * Checks a given integer.
     * Checks if the ingteger is below 0 or not.
     * If the integer is not
     * @param n
     * @param prefiks
     * @return
     */
    private int integerChecker(int n, String prefiks) {
        if(n < 0) {
            throw new IllegalArgumentException("The integer " + prefiks + " cant be below 0");
        }
        return n;
    }

    /**
     * Return oid.
     * @return oid
     */
    public int getOid() {
        return oid;
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
            LOGGER.warning(EXCEPTION_WARNING_MASSAGE + illegalArgumentException.getMessage());
        }
    }

    /**
     * Return uid
     * @return uid
     */
    public User getUid() {
        return user;
    }

    /**
     * Setts the uid.
     * @param user the uid that you want to set.
     */
    public void setUid(User user) {
        this.user = user;
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
}
