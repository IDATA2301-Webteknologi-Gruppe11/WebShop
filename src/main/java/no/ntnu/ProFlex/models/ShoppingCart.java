package no.ntnu.ProFlex.models;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represent shopping cart
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
     * Empty constructor that is needed for JPA
     */
    public ShoppingCart() {

    }

    /**
     * Return scid
     * @return scid
     */
    public int getId() {
        return this.id;
    }

    /**
     * Return user
     * @return user
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Return items
     * @return list of items
     */
    public List<CartItem> getCartItems() {
        return this.cartItems;
    }

    /**
     * Setts id of the shoppin cart
     * @param id the new scid of the cart
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setts user of the cart
     * @param user the user of the cart
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Setts the items of the cart
     * @param cartItems list of items that you want in the cart
     */
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public boolean isValid() {
        return this.id >= 0;
    }
}
