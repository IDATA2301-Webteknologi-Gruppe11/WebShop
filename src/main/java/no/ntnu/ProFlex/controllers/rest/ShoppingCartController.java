package no.ntnu.ProFlex.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.ShoppingCart;
import no.ntnu.ProFlex.services.ShoppingCartService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class represent the controller endpoint class for shopping cart
 *
 * @author Ole Kristian
 * @version 1.0
 */
@RestController
@RequestMapping("/api/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    /**
     * Returns all the shopping cart.
     *
     * @return all shopping carts
     */
    @Operation(summary = "Get all shopping carts", description = "Returns all the shopping carts")
    @GetMapping("/getAll")
    public ResponseEntity<List<ShoppingCart>> getShoppingCarts() {
        Iterable<ShoppingCart> shoppingCarts = this.shoppingCartService.getAll();
        if (!shoppingCarts.iterator().hasNext()) {
            return new ResponseEntity("Didn't find shopping carts", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<ShoppingCart>) shoppingCarts);
    }

    /**
     * Returns the shopping cart of a given ID.
     *
     * @param id the ID of the shopping cart to retrieve
     * @return the shopping cart of the given ID
     */
    @Operation(summary = "Get shopping cart by ID", description = "Retrieves a shopping cart by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartFromGivenId(
            @Parameter(name = "id", description = "ID of the shopping cart to retrieve", required = true)
            @PathVariable int id) {
        ShoppingCart shoppingCart = this.shoppingCartService.findById(id);
        if (shoppingCart == null) {
            return new ResponseEntity("Didn't find shopping cart", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(shoppingCart);
    }

    /**
     * Creates and adds a shopping cart.
     *
     * @param shoppingCart the shoppingCart that is getting created
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while creating the product
     */
    @Operation(summary = "Adds a shoppingCart", description = "Creates and adds a shopping cart to the shopping cart repository.")
    @PostMapping("/add")
    public ResponseEntity<?> addShoppingCart(
            @Parameter(name = "shopping cart", description = "The shopping cart that is created", required = true)
            @RequestBody ShoppingCart shoppingCart) {
        try {
            if (!this.shoppingCartService.add(shoppingCart)) {
                return new ResponseEntity("Shopping cart was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Shopping cart was added", HttpStatus.CREATED);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the shopping cart for a given ID.
     *
     * @param id the ID of the shopping cart to update
     * @param shoppingCart new shopping cart of the shopping cart
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while updating the product
     */
    @Operation(summary = "Update shopping cart", description = "Update the shopping cart from the shopping cart repository")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShoppingCart(
            @Parameter(name = "id", description = "ID of the shopping cart to update", required = true)
            @PathVariable int id,
            @Parameter(name = "shoppingCart", description = "The new shopping cart that you want the old one to change to", required = true)
            @PathVariable ShoppingCart shoppingCart) {
        try {
            ShoppingCart oldShoppingCart = this.shoppingCartService.findById(id);
            if (oldShoppingCart == null) {
                return new ResponseEntity("didn't find shopping cart", HttpStatus.NOT_FOUND);
            }
            this.shoppingCartService.update(id, shoppingCart);
            if (this.shoppingCartService.findById(id) == null) {
                return new ResponseEntity("Shopping cart didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Shopping cart was updated", HttpStatus.NO_CONTENT);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a shopping cart from the shopping cart with the given ID.
     *
     * @param id the ID of the shopping cart to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException  if an error occurs while deleting the product
     */
    @Operation(summary = "Delete shopping cart", description = "Delete a shopping cart from the shopping cart repository given its ID")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ShoppingCart> deleteShoppingCart(
            @Parameter(name = "id", description = "ID of the shopping cart to delete", required = true)
            @PathVariable int id) {
        try {
            if (!this.shoppingCartService.delete(id)) {
                return new ResponseEntity("Shopping cart was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Shopping cart was removed", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
