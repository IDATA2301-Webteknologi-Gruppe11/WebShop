package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Returns all orders.
     *
     * @return all orders.
     */
    public Iterable<Order> getAll() {
        return this.orderRepository.findAll();
    }

    /**
     * Finds and return an order by id.
     *
     * @param id the id of the order that you want to find.
     * @return the order.
     */
    public Order findById(int id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    /**
     * Adds an order to the order repository.
     *
     * @param order the order you want to add.
     * @return A boolean statement. True if the order was added, false if it was not.
     */
    public boolean add(Order order) {
        boolean added = false;
        if(canBeAdded(order)) {
            this.orderRepository.save(order);
            added = true;
        }
        return added;
    }

    /**
     * Checks if the order can be added to the repository.
     *
     * @param order the order that you want to check.
     * @return A boolean statement. True if it can be added, false if it can not.
     */
    private boolean canBeAdded(Order order) {
        return order != null && order.isValid();
    }

    /**
     * Removes an order form the order repository.
     *
     * @param id the id of the order that you want to have removed.
     * @return A boolean statement. True if the order was removed, false if not.
     */
    public boolean delete(int id) {
        boolean deleted = false;
        if(findById(id) != null) {
            this.orderRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update an existing order.
     *
     * @param id the id of the order that you want to update.
     * @param order the new order that you want the old order to be updated to.
     */
    public void update(int id, Order order) {
        Order existingOrder = findById(id);
        String errorMessage = null;
        if (existingOrder == null) {
            errorMessage = "No order exists with the id " + id;
        }
        if (order == null || !order.isValid()) {
            errorMessage = "Wrong data in request body";
        }
        else if(order.getOid() != id) {
            errorMessage = "The ID of the product in the URL does not match anny ID in the JSON data";
        }
        if (errorMessage == null) {
            this.orderRepository.save(order);
        }
    }
}
