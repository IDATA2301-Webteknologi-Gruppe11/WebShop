package no.ntnu.ProFlex.controllers.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.services.OrderService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class represent the controller for order.
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@RestController
@RequestMapping("/api/order")
@Api(value = "Order API", tags = "Order")
public class OrderController {

    @Autowired
    private OrderService orderService = new OrderService();

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(OrderController.class.getName());


    /**
     * Returns all the orders.
     *
     * @return all orders
     */
    @ApiOperation(value = "Get orders", notes = "Find and return all orders for the order repository and return http status")
    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getOrders() {
        Iterable<Order> orders = this.orderService.getAll();
        if (!orders.iterator().hasNext()) {
            return new ResponseEntity("Didn't find orders", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<Order>) orders);
    }

    /**
     * Returns the order of a given ID.
     *
     * @param id the ID of the order to retrieve
     * @return the order of the given ID
     */
    @ApiOperation(value = "Get order by id", notes = "finds and return order by id. Also returns http status")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderFromId(
            @Parameter(name = "id", description = "The id of the order that you want to find", required = true)
            @PathVariable int id) {
        Order order = this.orderService.findById(id);
        if (order == null) {
            return new ResponseEntity("Didn't find order", HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(order);
        }
    }

    /**
     * Creates and adds an order.
     *
     * @param order the order that is getting created
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while creating the order
     */
    @ApiOperation(value = "Create an order.", notes = "Create and add a order to the order repository")
    @PostMapping("/add")
    public ResponseEntity<Order> createOrder(
            @Parameter(name = "order", description = "the order that you want to add", required = true)
            @RequestBody Order order) {
        try {
            if (!this.orderService.add(order)) {
                return new ResponseEntity("Order was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity(order, HttpStatus.CREATED);
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the order for a given ID.
     *
     * @param id the ID of the order  to update
     * @param order new order of the order
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while updating the product
     */
    @ApiOperation(value = "Update a order", notes = "Update an existing order form the order repository")
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(
            @Parameter(name = "id", description = "the id of the product you want to update", required = true)
            @PathVariable int id,
            @Parameter(name = "order", description = "the order that you want the existing order to be updated to", required = true)
            @RequestBody Order order) {
        try {
            Order oldOrder = this.orderService.findById(id);
            if (oldOrder == null) {
                return new ResponseEntity("didn't find order", HttpStatus.NOT_FOUND);
            }
            this.orderService.update(id, order);
            if (this.orderService.findById(id) == null) {
                return new ResponseEntity("Order didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok(order);
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes an order from the order list with the given ID.
     *
     * @param id the ID of the order to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException  if an error occurs while deleting the product
     */
    @ApiOperation(value = "Remove order", notes = "Removes a order form the order repository")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Order> deleteOrderOrder(
            @Parameter(name = "id", description = "the id of the order that you want to remove", required = true)
            @PathVariable int id) {
        try {
            if (!this.orderService.delete(id)) {
                return new ResponseEntity("Order was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Order was removed", HttpStatus.OK);
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
