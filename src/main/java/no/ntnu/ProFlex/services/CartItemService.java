package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.CartItem;
import no.ntnu.ProFlex.models.ShoppingCart;
import no.ntnu.ProFlex.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class represent the service clas for cart items.
 *
 * @author Ole Kristian
 * @version 1.0
 */
@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    /**
     * Returns all Cart items
     *
     * @return all cart items
     */
    public Iterable<CartItem> getAll() {
        return this.cartItemRepository.findAll();
    }

    /**
     * Returns all cart items based on the shopping cart.
     * @param scid the id of the shoipping cart
     * @return list of cart items
     */
    public List<CartItem> findAllByScid(ShoppingCart scid) {
        return this.cartItemRepository.findAllByScid(scid);
    }

    /**
     * Find and return a cart item for a given id
     *
     * @param id of the cart item
     * @return cart item
     */
    public CartItem findById(int id) {
        return this.cartItemRepository.findById(id).orElse(null);
    }

    /**
     * Add a cart item to the cart repository
     *
     * @param cartItem cart item you want to add
     * @return boolean statement. True if added, false if not.
     */
    public boolean add(CartItem cartItem) {
        boolean added = false;
        if(canBeAdded(cartItem)) {
            this.cartItemRepository.save(cartItem);
            added = true;
        }
        return added;
    }

    /**
     * Checks if the cart item is valid
     *
     * @param cartItem cart item you want to check
     * @return boolean statement. True if okey, false if not.
     */
    private boolean canBeAdded(CartItem cartItem) {
        return cartItem != null && cartItem.isValid();
    }

    /**
     * Removes a cart item form the repository.
     *
     * @param id of the cart item that you want to remove.
     * @return boolean statement. True if removed, false if not.
     */
    public boolean delete(int id) {
        boolean deleted = false;
        if(findById(id) != null) {
            this.cartItemRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update an existing cart item.
     *
     * @param id of the cart item you want to update.
     * @param cartItem the cart item you want the old one to be updated to.
     */
    public void update(int id, CartItem cartItem) {
        CartItem existingCartItem = findById(id);
        String errorMessage = null;
        if (existingCartItem == null) {
            errorMessage = "No shopping cart exists with the id " + id;
        }
        if (cartItem == null || !cartItem.isValid()) {
            errorMessage = "Wrong data in request body";
        }
        else if(cartItem.getCiid() != id) {
            errorMessage = "The ID of the shopping cart in the URL does not match anny ID in the JSON data";
        }
        System.out.println(errorMessage);
        if (errorMessage == null) {
            this.cartItemRepository.save(cartItem);
        }
    }

    /**
     * Update the quantity of the cart item.
     * @param ciid id of the cart item
     * @param quantity the new number of quantity you want.
     */
    public void updateQuantity(int ciid, int quantity) {
        CartItem cartItem = findById(ciid);
        String errorMassage = null;
        if(cartItem == null) {
            errorMassage = "no cart item found";
        }
        if(errorMassage != null) {
            cartItem.setQuantity(quantity);
            this.cartItemRepository.save(cartItem);
        }
    }
}
