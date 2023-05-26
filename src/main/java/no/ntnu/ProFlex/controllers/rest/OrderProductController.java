package no.ntnu.ProFlex.controllers.rest;

import no.ntnu.ProFlex.models.OrderProduct;
import no.ntnu.ProFlex.services.OrderProductService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class is the rest endpoint controller class for orderProduct
 */
@RestController
@RequestMapping("/api/orderproduct")
public class OrderProductController {

    @Autowired
    OrderProductService orderProductService;

    private static final Logger LOGGER = Logger.getLogger(OrderProductController.class.getName());
    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderProduct>> getOrderProducts() {
        Iterable<OrderProduct> orderProducts = this.orderProductService.findAll();
        if(!orderProducts.iterator().hasNext()) {
            return new ResponseEntity("Didn't find prder product", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<OrderProduct>) orderProducts);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOrderProduct(
            @RequestBody OrderProduct orderProduct) {
        try {
            if(!this.orderProductService.add(orderProduct)) {
                return new ResponseEntity("Order Product was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Order Product was added", HttpStatus.CREATED);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
