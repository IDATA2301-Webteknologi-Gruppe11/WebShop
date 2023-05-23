package no.ntnu.ProFlex.models;

import jakarta.persistence.*;

@Entity
@Table(name = "order_product")
public class OrderProduct {

    @Id
    @GeneratedValue
    private int opid;

    @OneToOne
    @JoinColumn(name = "oid")
    private Order oid;

    @OneToOne
    @JoinColumn(name = "pid")
    private Product pid;

    @Column(name = "quantity")
    private int quantity;

    /**
     * Empty constructor for JPA
     */
    public OrderProduct() {

    }

    public int getOpid() {
        return opid;
    }

    public Order getOid() {
        return oid;
    }

    public Product getPid() {
        return pid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setOpid(int opid) {
        this.opid = opid;
    }

    public void setOrder(Order oid) {
        this.oid = oid;
    }

    public void setProduct(Product pid) {
        this.pid = pid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
