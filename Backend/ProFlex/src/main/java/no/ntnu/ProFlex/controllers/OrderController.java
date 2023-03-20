package no.ntnu.ProFlex.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * This class represent the controller for order.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService = new OrderService();

    /**
     * Get all orders.
     * Also, https status. OK if it works, NOT FOUND is anything is found.
     *
     * @return All orders, https status.
     */
    @Operation(summary = "Get orders", description = "Find and return all orders for the order repository and return https status")
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        Iterable<Order> orders = this.orderService.getAll();
        if (!orders.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((List<Order>) orders);
    }

    /**
     * Get an order form a id.
     * Also, https status. OK if it works, NOT FOUND if anything is not found.
     *
     * @param id of the order that you want to find.
     * @return A order, http status
     */
    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrderFromId(
            @Parameter(name = "id", description = "The id of the order that you want to find") @PathVariable int id) {
        Order order = this.orderService.findById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(order);
        }
    }

    /**
     * Create and order.
     * Also, http status. OK if it works, BAD REQUEST if something is wrong with the
     * order.
     *
     * @param order The order that you want to create.
     * @return Created order, http status
     */
    @Operation(summary = "Create an order.", description = "Create and add a order to the order repository")
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(
            @Parameter(name = "order", description = "the order that you want to add") Order order) {
        if (!this.orderService.add(order)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.ok(order);
        }
    }

    /**
     * Update an existing order.
     * Also, http status. OK if it worked, NOT FOUND if the order from the given id
     * is not found and INTERNAL SERVER ERROR if it did not work.
     *
     * @param id    the id of the order that you want to update.
     * @param order the order that the existing order you want to update to.
     * @return the updated order, http status.
     */
    @Operation(summary = "Update a order", description = "Update an existing order form the order repository")
    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(
            @Parameter(name = "id", description = "the id of the product you want to update") @PathVariable int id,
            @Parameter(name = "order", description = "the order that you want the existing order to be updated to") @RequestBody Order order) {
        Order oldOrder = this.orderService.findById(id);
        if (oldOrder == null) {
            return ResponseEntity.notFound().build();
        }
        this.orderService.update(id, order);
        if (this.orderService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok(order);
        }
    }

    /**
     * Remove an order.
     * Also, http status. OK if it worked, INTERNAL SERVER ERROR if it did not work.
     *
     * @param id of the order that you want to remove.
     * @return https status.
     */
    @Operation(summary = "Remove order", description = "Removes a order form the order repository")
    @DeleteMapping("/order/{id}")
    public ResponseEntity<Order> deleteOrderOrder(
            @Parameter(name = "id", description = "the id of the order that you want to remove") @PathVariable int id) {
        if (!this.orderService.delete(id)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
