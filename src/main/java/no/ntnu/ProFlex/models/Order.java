package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import java.util.Date;

/**
 * This class represent the orders for the users.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@ApiModel(description = "Represent a order")
@Entity
@Table(name = "Orders")
public class Order {

    @ApiModelProperty(value = "Id for order")
    @Id
    @GeneratedValue
    private int id;

    @ApiModelProperty(value = "The date when the order was placed.")
    private Date date;

    @ApiModelProperty(value = "User of the order")
    @ManyToOne
    @JoinColumn(name = "orders")
    @JsonBackReference(value = "user-order")
    private User user;

    /**
     * Default constructor
     * @param date the date of the order
     */
    public Order(Date date, User user) {
        this.date = date;
        this.user = user;
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public Order() {

    }

    /**
     * Setts oid.
     * @param id the oid that you want to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return oid.
     * @return oid
     */
    public int getId() {
        return id;
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
     * @param user the user you want
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return the user for the order
     * @return user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Checks of the order is valid
     * @return boolean statement. True if order is valid, false if not.
     */
    public boolean isValid() {
        return this.date != null && this.user != null;
    }
}
