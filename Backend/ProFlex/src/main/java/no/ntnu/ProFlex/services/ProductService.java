package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Represent the service class for product.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Returns all the products.
     *
     * @return all products
     */
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    /**
     * Find and return a product for a given ID.
     *
     * @param id of the product you want to find.
     * @return the product.
     */
    public Product findById(int id) {
        return this.productRepository.findById(id).orElse(null);
    }

    /**
     * Adds a product to the product repository.
     *
     * @param product the product you want to add.
     * @return a boolean statement if the product was added or not. True if added, false if not added.
     */
    public boolean add(Product product) {
        boolean added = false;
        if(canBeAdded(product)) {
            this.productRepository.save(product);
            added = true;
        }
        return added;
    }

    /**
     * Checks if the product can be added to the product repository.
     *
     * @param product the product you want to check.
     * @return a boolean statement if the product can be added or not. True if it can, false if it can not.
     */
    private boolean canBeAdded(Product product) {
        return product != null && product.isValid();
    }

    /**
     * Delete a product for the product repository
     *
     * @param id the id of the product you want to delete form the repository
     * @return a boolean statment if the product was deleted or not. True if deleted, false if not.
     */
    public boolean delete(int id) {
        boolean deleted = false;
        if(findById(id) != null) {
            this.productRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /** 
     * Update a product in the product repository
     *
     * @param id of the product that you want to update
     * @param product the product you want the old one to be updated to.
     */
    public void update(int id, Product product) {
        Product existingProduct = findById(id);
        String errorMessage = null;
        if (existingProduct == null) {
            errorMessage = "No product exists with the id " + id;
        }
        if (product == null || !product.isValid()) {
            errorMessage = "Wrong data in request body";
        }
        else if(product.getId() != id) {
            errorMessage = "The ID of the product in the URL does not match anny ID in the JSON data";
        }
        if (errorMessage == null) {
            this.productRepository.save(product);
        }
    }
}