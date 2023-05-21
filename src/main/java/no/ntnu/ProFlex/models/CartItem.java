package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "CartItem")
public class CartItem {

    @Id
    @GeneratedValue
    private int ciid;

    @ManyToOne
    @JoinColumn(name = "scid")
    @JsonBackReference
    private ShoppingCart scid;

    @ManyToOne
    @JoinColumn(name = "pid")
    @JsonBackReference
    private Product pid;

    private int quantity;

    /**
     * Empty constructor that is needed for JPA
     */
    public CartItem() {

    }

    /**
     *
     * @return
     */
    public int getCiid() {
        return this.ciid;
    }

    /**
     *
     * @return
     */
    public ShoppingCart getCart() {
        return scid;
    }

    /**
     *
     * @return
     */
    public Product getProduct() {
        return pid;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param ciid
     */
    public void setCiid(int ciid) {
        this.ciid = ciid;
    }

    /**
     *
     * @param cart
     */
    public void setCart(ShoppingCart cart) {
        this.scid = cart;
    }

    /**
     *
     * @param product
     */
    public void setProduct(Product product) {
        this.pid = product;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return
     */
    public boolean isValid() {
        return this.ciid >= 0;
    }
}
