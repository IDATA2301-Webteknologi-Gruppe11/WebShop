package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

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

    @PersistenceContext
    private EntityManager entityManager;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Find and return a product form a given id.
     *
     * @param id the uniqe integer id for the product that you want to find.
     * @return the product.
     */
    public Optional<Product> findById(int id) {
        return productRepository.findById(id);
    }

    /**
     * Find and returns all the products.
     *
     * @return list of products
     */
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    /**
     * Add product
     *
     * @param product
     */
    public void add(Product product) {
        productRepository.save(product);
    }

    /**
     * Remove Product
     *
     * @param id
     * @return
     */
    public boolean remove(int id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if(existingProduct == null) {
            return false;
        }
        productRepository.delete(existingProduct);
        return true;
    }

    /**
     * Update product.
     *
     * @param id
     * @param product
     * @return
     */
    public Product updateProduct(int id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if(existingProduct == null) {
            return null;
        }
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getName());
        return productRepository.save(existingProduct);
    }
}
