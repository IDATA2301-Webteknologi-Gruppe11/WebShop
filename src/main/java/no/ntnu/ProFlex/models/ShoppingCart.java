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
    private int scid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    @JsonBackReference(value = "user-shoppingcart")
    private User uid;

    @OneToMany(mappedBy = "scid")
    @JsonManagedReference(value = "cartitem-shoppingcart")
    private List<CartItem> ciid = new ArrayList<>();

    /**
     * Empty constructor that is needed for JPA
     */
    public ShoppingCart() {

    }

    /**
     * Return scid
     * @return scid
     */
    public int getScid() {
        return this.scid;
    }

    /**
     * Return user
     * @return user
     */
    public User getUid() {
        return this.uid;
    }

    /**
     * Return items
     * @return list of items
     */
    public List<CartItem> getCiid() {
        return this.ciid;
    }

    /**
     * Setts id of the shoppin cart
     * @param id the new scid of the cart
     */
    public void setScid(int id) {
        this.scid = id;
    }

    /**
     * Setts user of the cart
     * @param uid the user of the cart
     */
    public void setUid(User uid) {
        this.uid = uid;
    }

    /**
     * Setts the items of the cart
     * @param ciid list of items that you want in the cart
     */
    public void setCiid(List<CartItem> ciid) {
        this.ciid = ciid;
    }

    public boolean isValid() {
        return this.scid >= 0;
    }
}
