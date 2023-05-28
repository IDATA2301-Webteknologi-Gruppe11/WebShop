package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;

@ApiModel(description = "Represent cartItem")
@Entity
@Table(name = "CartItem")
public class CartItem {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "cart item id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "shoppingCart")
    @JsonBackReference(value = "cartitem-shoppingcart")
    @ApiModelProperty(value = "cart item shopping cart")
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name = "product")
    @JsonIgnoreProperties("cartItems")
    @ApiModelProperty(value = "product in the cart item")
    private Product product;

    @ApiModelProperty(value = "Quantity of product in cart item")
    private int quantity;

    /**
     * Constructs a new CartItem object.
     * This constructor is needed for JPA.
     */
    public CartItem() {

    }

    /**
     * Retrieves the ID of the CartItem.
     *
     * @return the ID of the CartItem
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retrieves the ShoppingCart associated with the CartItem.
     *
     * @return the ShoppingCart associated with the CartItem
     */
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    /**
     * Retrieves the Product associated with the CartItem.
     *
     * @return the Product associated with the CartItem
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Retrieves the quantity of the CartItem.
     *
     * @return the quantity of the CartItem
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the ID of the CartItem.
     *
     * @param id the ID of the CartItem
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the ShoppingCart associated with the CartItem.
     *
     * @param shoppingCart the ShoppingCart to associate with the CartItem
     */
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Sets the Product associated with the CartItem.
     *
     * @param product the Product to associate with the CartItem
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Sets the quantity of the CartItem.
     *
     * @param quantity the quantity of the CartItem
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Checks if the CartItem is valid.
     *
     * @return true if the CartItem is valid, false otherwise
     */
    public boolean isValid() {
        return this.id >= 0;
    }
}