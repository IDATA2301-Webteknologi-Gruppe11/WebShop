package no.ntnu.ProFlex.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;

@ApiModel(description = "Represent the relation ship between order and product")
@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "id of orderProduct")
    private int id;

    @OneToOne
    @JoinColumn(name = "orders")
    @ApiModelProperty(value = "The order of orderProduct")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product")
    @ApiModelProperty(value = "the product of orderProduct")
    private Product product;

    @Column(name = "quantity")
    @ApiModelProperty(value = "quantity of product that is orderd")
    private int quantity;


    @Column(name = "lisensKey")
    @ApiModelProperty("The lisens key for product")
    private String lisensKey;

    /**
     * Empty constructor that is needed for JPA.
     */
    public OrderProduct() {

    }

    /**
     * Retrieves the ID of the OrderProduct.
     *
     * @return the ID of the OrderProduct
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the Order associated with the OrderProduct.
     *
     * @return the Order associated with the OrderProduct
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Retrieves the Product associated with the OrderProduct.
     *
     * @return the Product associated with the OrderProduct
     */
    public Product getProduct() {
        return this.product;
    }

    /**
     * Retrieves the quantity of the OrderProduct.
     *
     * @return the quantity of the OrderProduct
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Retrieves the license key of the OrderProduct.
     *
     * @return the license key of the OrderProduct
     */
    public String getLisensKey() {
        return lisensKey;
    }

    /**
     * Sets the ID of the OrderProduct.
     *
     * @param id the ID of the OrderProduct
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the Order associated with the OrderProduct.
     *
     * @param order the Order to associate with the OrderProduct
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Sets the Product associated with the OrderProduct.
     *
     * @param product the Product to associate with the OrderProduct
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Sets the quantity of the OrderProduct.
     *
     * @param quantity the quantity of the OrderProduct
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the license key of the OrderProduct.
     *
     * @param lisensKey the license key of the OrderProduct
     */
    public void setLisensKey(String lisensKey) {
        this.lisensKey = lisensKey;
    }

    /**
     * Checks if the OrderProduct is valid.
     *
     * @return true if the OrderProduct is valid, false otherwise
     */
    public boolean isValid() {
        return !"".equals(lisensKey);
    }
}