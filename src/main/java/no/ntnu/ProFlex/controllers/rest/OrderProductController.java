package no.ntnu.ProFlex.controllers.rest;

import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.OrderProduct;
import no.ntnu.ProFlex.services.OrderProductService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This class represent the controller class for entity order product
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@RestController
@RequestMapping("/api/orderproduct")
@Api(value = "OrderProduct API", tags = "OrderProduct")
public class OrderProductController {

    @Autowired
    OrderProductService orderProductService;

    private static final Logger LOGGER = Logger.getLogger(OrderProductController.class.getName());
    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";

    /**
     * Returns all order products
     * @return all order product
     */
    @ApiOperation(value = "Get all OrderProducts", notes = "Retrieves all OrderProducts")
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderProduct>> getOrderProducts() {
        Iterable<OrderProduct> orderProducts = this.orderProductService.findAll();
        if (!orderProducts.iterator().hasNext()) {
            return new ResponseEntity("Didn't find order products", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<OrderProduct>) orderProducts);
    }

    /**
     * Add a order product to the database
     * @param orderProduct the json that you want to create order from
     * @return https status of the operation
     */
    @ApiOperation(value = "Add OrderProduct", notes = "Adds a new OrderProduct")
    @PostMapping("/add")
    public ResponseEntity<?> addOrderProduct(
            @Parameter(name = "orderProduct", description = "the new orderProduct you want to update the old to.")
            @RequestBody OrderProduct orderProduct) {
        try {
            if (!this.orderProductService.add(orderProduct)) {
                return new ResponseEntity<>("Order Product was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Order Product was added", HttpStatus.CREATED);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity<>(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}