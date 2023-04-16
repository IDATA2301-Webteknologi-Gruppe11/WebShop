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
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private ShoppingCart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    private int quantity;

    /**
     * Empty constructor that is needed for JPA
     */
    public CartItem() {

    }

    public int getCiid() {
        return this.ciid;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCiid(int ciid) {
        this.ciid = ciid;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isValid() {
        return this.ciid >= 0;
    }
}
