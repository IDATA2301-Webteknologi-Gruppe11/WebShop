package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.ShoppingCart;
import no.ntnu.ProFlex.controllers.web.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class represent the service class for shopping cart
 *
 * @author Ole Kristian
 * @version 1.0
 */
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    /**
     * Returns all Shopping carts.
     *
     * @return all shopping carts.
     */
    public Iterable<ShoppingCart> getAll() {
        return this.shoppingCartRepository.findAll();
    }

    /**
     * Find and return a shopping cart for a given ID.
     *
     * @param id of the shopping cart
     * @return shopping cart
     */
    public ShoppingCart findById(int id) {
        return this.shoppingCartRepository.findById(id).orElse(null);
    }

    /**
     * Add a shopping cart to the shopping cart repository.
     *
     * @param shoppingCart shopping cart you want to add
     * @return boolean statement. True if added, false if not.
     */
    public boolean add(ShoppingCart shoppingCart) {
        boolean added = false;
        if(canBeAdded(shoppingCart)) {
            this.shoppingCartRepository.save(shoppingCart);
            added = true;
        }
        return added;
    }

    /**
     * Checks if shopping cart can be added.
     *
     * @param shoppingCart the shopping cart that you want to add
     * @return boolean statement. True if it can be added, false if not.
     */
    private boolean canBeAdded(ShoppingCart shoppingCart) {
        return shoppingCart != null && shoppingCart.isValid();
    }

    /**
     * Removes a shopping cart from the repository
     *
     * @param id of the cart that you want to remove
     * @return boolean statement. True if removed, false if not.
     */
    public boolean delete(int id) {
        boolean deleted = false;
        if(findById(id) != null) {
            this.shoppingCartRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update an existing shopping cart.
     *
     * @param id of the shopping cart you want to update.
     * @param shoppingCart the shopping cart you want the old one to be updated to.
     */
    public void update(int id, ShoppingCart shoppingCart) {
        ShoppingCart existingShoppingCart = findById(id);
        String errorMessage = null;
        if (existingShoppingCart == null) {
            errorMessage = "No shopping cart exists with the id " + id;
        }
        if (shoppingCart == null || !shoppingCart.isValid()) {
            errorMessage = "Wrong data in request body";
        }
        else if(shoppingCart.getScid() != id) {
            errorMessage = "The ID of the shopping cart in the URL does not match anny ID in the JSON data";
        }
        if (errorMessage == null) {
            this.shoppingCartRepository.save(shoppingCart);
        }
    }
}
