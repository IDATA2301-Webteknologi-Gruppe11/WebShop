package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Iterable<Order> getAll() {
        return this.orderRepository.findAll();
    }

    public Order findById(int id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    public boolean add(Order order) {
        boolean added = false;
        if(canBeAdded(order)) {
            this.orderRepository.save(order);
            added = true;
        }
        return added;
    }

    private boolean canBeAdded(Order order) {
        return order != null && order.isValid();
    }

    public boolean delete(int id) {
        boolean deleted = false;
        if(findById(id) != null) {
            this.orderRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

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
