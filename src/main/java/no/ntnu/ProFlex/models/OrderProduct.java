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
    public int getOpid() {
        return opid;
    }

    /**
     *
     * @return
     */
    public Order getOid() {
        return oid;
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
     * @return
     */
    public String getLisensKLey() {
        return lisensKey;
    }

    /**
     *
     * @param opid
     */
    public void setOpid(int opid) {
        this.opid = opid;
    }

    /**
     *
     * @param oid
     */
    public void setOid(Order oid) {
        this.oid = oid;
    }

    /**
     *
     * @param pid
     */
    public void setPid(Product pid) {
        this.pid = pid;
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
     * @param lisensKLey
     */
    public void setLisensKLey(String lisensKLey) {
        this.lisensKey = lisensKLey;
    }

    /**
     * Checks if orderProduct is valid
     * @return
     */
    public boolean isValid() {
        return !"".equals(lisensKey);
    }
}
