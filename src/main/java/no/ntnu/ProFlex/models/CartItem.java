package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "CartItem")
public class CartItem {

    @Id
    @GeneratedValue
    private int ciid;

    @ManyToOne
    @JoinColumn(name = "scid")
    @JsonBackReference(value = "cartitem-shoppingcart")
    private ShoppingCart scid;

    @ManyToOne
    @JoinColumn(name = "pid")
    @JsonIgnoreProperties("ciid")
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
    public ShoppingCart getScid() {
        return scid;
    }

    /**
     *
     * @return
     */
    public Product getPid() {
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
     * @param scid
     */
    public void setScid(ShoppingCart scid) {
        this.scid = scid;
    }

    /**
     *
     * @param product
     */
    public void setPid(Product product) {
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
