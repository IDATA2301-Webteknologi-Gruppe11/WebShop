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

    /**
     * Default constructor
     * @param date the date of the order
     */
    public Order(Date date, User user) {
        this.date = date;
        this.uid = user;
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Order() {

    }

    /**
     * Setts oid.
     * @param oid the oid that you want to set.
     */
    public void setOid(int oid) {
        this.oid = oid;
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

    /**
     * Setts the user
     * @param uid the user you want
     */
    public void setUid(User uid) {
        this.uid = uid;
    }

    /**
     * Return the user for the order
     * @return user
     */
    public User getUid() {
        return this.uid;
    }

    /**
     * Checks of the order is valid
     * @return boolean statement. True if order is valid, false if not.
     */
    public boolean isValid() {
        return this.date != null && this.uid != null;
    }
}
