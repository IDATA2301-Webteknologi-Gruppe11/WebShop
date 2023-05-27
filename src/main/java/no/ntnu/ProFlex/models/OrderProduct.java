package no.ntnu.ProFlex.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "orders")
    private Order order;

    @OneToOne
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "lisensKey")
    private String lisensKey;

    /**
     * Empty constructor for JPA
     *
     */
    public OrderProduct() {

    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public Order getOrder() {
        return order;
    }

    /**
     *
     * @return
     */
    public Product getProduct() {
        return this.product;
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
     * @return
     */
    public String getLisensKey() {
        return lisensKey;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     *
     * @param product
     */
    public void setProduct(Product product) {
        this.product = product;
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
     * @param lisensKey
     */
    public void setLisensKey(String lisensKey) {
        this.lisensKey = lisensKey;
    }

    /**
     * Checks if orderProduct is valid
     * @return
     */
    public boolean isValid() {
        return !"".equals(lisensKey);
    }
}
