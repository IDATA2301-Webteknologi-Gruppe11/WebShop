package no.ntnu.ProFlex.controllers.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * This class represents the controller endpoint class for shopping carts.
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/shoppingcart")
@Api(value = "Shopping Cart API", tags = "ShoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    /**
     * Retrieves all shopping carts.
     *
     * @return a ResponseEntity with the list of all shopping carts
     */
    @ApiOperation(value = "Get all shopping carts", notes = "Retrieves all the shopping carts")
    @GetMapping("/getAll")
    public ResponseEntity<List<ShoppingCart>> getShoppingCarts() {
        Iterable<ShoppingCart> shoppingCarts = this.shoppingCartService.getAll();
        if (!shoppingCarts.iterator().hasNext()) {
            return new ResponseEntity("No shopping carts found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<ShoppingCart>) shoppingCarts);
    }

    /**
     * Retrieves a shopping cart by its ID.
     *
     * @param id the ID of the shopping cart to retrieve
     * @return a ResponseEntity with the shopping cart of the given ID
     */
    @ApiOperation(value = "Get shopping cart by ID", notes = "Retrieves a shopping cart by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartFromGivenId(
            @Parameter(name = "id", description = "ID of the shopping cart to retrieve", required = true)
            @PathVariable int id) {
        ShoppingCart shoppingCart = this.shoppingCartService.findById(id);
        if (shoppingCart == null) {
            return new ResponseEntity("Shopping cart not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(shoppingCart);
    }

    /**
     * Creates and adds a shopping cart.
     *
     * @param shoppingCart the shopping cart to be created and added
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @throws JSONException if an error occurs while creating the shopping cart
     */
    @ApiOperation(value = "Add shopping cart", notes = "Creates and adds a shopping cart to the shopping cart repository")
    @PostMapping("/add")
    public ResponseEntity<?> addShoppingCart(
            @Parameter(name = "shoppingCart", description = "The shopping cart to be created", required = true)
            @RequestBody ShoppingCart shoppingCart) {
        try {
            if (!this.shoppingCartService.add(shoppingCart)) {
                return new ResponseEntity<>("Shopping cart was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Shopping cart was added", HttpStatus.CREATED);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity<>(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Updates the shopping cart for a given ID.
     *
     * @param id           the ID of the shopping cart to update
     * @param shoppingCart the new shopping cart to replace the existing one
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @throws JSONException if an error occurs while updating the shopping cart
     */
    @ApiOperation(value = "Update shopping cart", notes = "Update the shopping cart from the shopping cart repository")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShoppingCart(
            @Parameter(name = "id", description = "ID of the shopping cart to update", required = true)
            @PathVariable int id,
            @Parameter(name = "shoppingCart", description = "The new shopping cart that replaces the existing one", required = true)
            @PathVariable ShoppingCart shoppingCart) {
        try {
            ShoppingCart oldShoppingCart = this.shoppingCartService.findById(id);
            if (oldShoppingCart == null) {
                return new ResponseEntity<>("Shopping cart not found", HttpStatus.NOT_FOUND);
            }
            this.shoppingCartService.update(id, shoppingCart);
            if (this.shoppingCartService.findById(id) == null) {
                return new ResponseEntity<>("Shopping cart update failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Shopping cart updated successfully", HttpStatus.NO_CONTENT);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity<>(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a shopping cart with the given ID.
     *
     * @param id the ID of the shopping cart to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @throws JSONException if an error occurs while deleting the shopping cart
     */
    @ApiOperation(value = "Delete shopping cart", notes = "Delete a shopping cart from the shopping cart repository given its ID")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<ShoppingCart> deleteShoppingCart(
            @Parameter(name = "id", description = "ID of the shopping cart to delete", required = true)
            @PathVariable int id) {
        try {
            if (!this.shoppingCartService.delete(id)) {
                return new ResponseEntity("Shopping cart deletion failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Shopping cart removed successfully", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
