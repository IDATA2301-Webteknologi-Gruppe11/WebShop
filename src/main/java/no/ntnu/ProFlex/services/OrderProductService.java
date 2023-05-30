package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.OrderProduct;
import no.ntnu.ProFlex.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for order product
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@Service
public class OrderProductService {

    @Autowired
    OrderProductRepository orderProductRepository;

    public Iterable<OrderProduct> findAll() {
        return this.orderProductRepository.findAll();
    }

    /**
     * Add a orderProduct to the orderProduct repository
     * @param orderProduct The orderProduct you want to add
     * @return boolean statement. True if added, false if not.
     */
    public boolean add(OrderProduct orderProduct) {
        boolean added = false;
        if(canBeAdded(orderProduct)) {
            this.orderProductRepository.save(orderProduct);
            System.out.println("im here ");
            added = true;
        }
        return added;
    }

    /**
     * Checks if the orderProduct can be added
     *
     * @param orderProduct the orderProduct you want to check
     * @return boolean true if it can be added, false if not
     */
    private boolean canBeAdded(OrderProduct orderProduct) {
        return orderProduct != null && orderProduct.isValid();
    }
}
