package no.ntnu.ProFlex.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.CartItem;
import no.ntnu.ProFlex.services.CartItemService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * This class represent the controller endpoint class for cart item.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cartItems")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    /**
     * Returns all the cart items.
     *
     * @return all cart items
     */
    @Operation(summary = "Get all cart items", description = "Returns all the cart items")
    @GetMapping("/getAll")
    public ResponseEntity<List<CartItem>> getCartItems() {
        Iterable<CartItem> cartItems = this.cartItemService.getAll();
        if (!cartItems.iterator().hasNext()) {
            return new ResponseEntity("Didn't find cart items", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<CartItem>) cartItems);
    }

    /**
     * Returns the cart item of a given ID.
     *
     * @param id the ID of the cart item to retrieve
     * @return the cart item of the given ID
     */
    @Operation(summary = "Get cart item by ID", description = "Retrieves a cart item by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemFromGivenId(
            @Parameter(name = "id", description = "ID of the cart item to retrieve", required = true)
            @PathVariable int id) {
        CartItem cartItem = this.cartItemService.findById(id);
        if (cartItem == null) {
            return new ResponseEntity("Didn't find cart item", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cartItem);
    }

    /**
     * Creates and adds a cart item.
     *
     * @param cartItem the cart item that is getting created
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while creating the product
     */
    @Operation(summary = "Adds a cart item", description = "Creates and adds a cart item to the shopping cart repository.")
    @PostMapping("/add")
    public ResponseEntity<?> addCartItem(
            @Parameter(name = "cart item", description = "The cart item that is created", required = true)
            @RequestBody CartItem cartItem) {
        try {
            if (!this.cartItemService.add(cartItem)) {
                return new ResponseEntity("Cart item was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Cart item was added", HttpStatus.CREATED);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the cart item for a given ID.
     *
     * @param id the ID of the cart item to update
     * @param cartItem new cart item of the cart item
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while updating the product
     */
    @Operation(summary = "Update cart item", description = "Update the cart item from the cart item repository")
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCartItem(
            @Parameter(name = "id", description = "ID of the cart item to update", required = true)
            @PathVariable int id,
            @Parameter(name = "cartItem", description = "The new cart item that you want the old one to change to", required = true)
            @RequestBody CartItem cartItem) {
        try {
            CartItem oldCartItem = this.cartItemService.findById(id);
            if (oldCartItem == null) {
                return new ResponseEntity("didn't find cart item", HttpStatus.NOT_FOUND);
            }
            System.out.println(cartItem.getCiid());
            this.cartItemService.update(id, cartItem);
            if (this.cartItemService.findById(id) == null) {
                return new ResponseEntity("Cart item didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Cart item cart was updated", HttpStatus.NO_CONTENT);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a cart item from the cart item with the given ID.
     *
     * @param id the ID of the cart item to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException  if an error occurs while deleting the product
     */
    @Operation(summary = "Delete cart item", description = "Delete a cart item from the cart item repository given its ID")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<CartItem> deleteCartItem(
            @Parameter(name = "id", description = "ID of the cart item to delete", required = true)
            @PathVariable int id) {
        try {
            if (!this.cartItemService.delete(id)) {
                return new ResponseEntity("Cart item was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Cart item was removed", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
