package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a shopping cart for a user.
 * It contains a list of cart items associated with the cart.
 * Each shopping cart is associated with a user.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@Schema(description = "Represent a shopping cart for a user")
@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    @JsonBackReference(value = "user-shoppingcart")
    private User user;

    @OneToMany(mappedBy = "shoppingCart")
    @JsonManagedReference(value = "cartitem-shoppingcart")
    private List<CartItem> cartItems = new ArrayList<>();

    /**
     * Empty constructor that is needed for JPA.
     */
    public ShoppingCart() {

    }

    /**
     * Returns the ID of the shopping cart.
     *
     * @return the ID of the shopping cart
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns the user associated with the shopping cart.
     *
     * @return the user associated with the shopping cart
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Returns the list of cart items in the shopping cart.
     *
     * @return the list of cart items in the shopping cart
     */
    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

    /**
     * Sets the ID of the shopping cart.
     *
     * @param id the new ID of the shopping cart
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the user associated with the shopping cart.
     *
     * @param user the user associated with the shopping cart
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the list of cart items in the shopping cart.
     *
     * @param cartItems the list of cart items to be set in the shopping cart
     */
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    /**
     * Checks whether this shopping cart object is valid.
     * A shopping cart object is considered valid if its ID is greater than or equal to 0.
     *
     * @return true if the shopping cart is valid, false otherwise
     */
    public boolean isValid() {
        return this.id >= 0;
    }
}