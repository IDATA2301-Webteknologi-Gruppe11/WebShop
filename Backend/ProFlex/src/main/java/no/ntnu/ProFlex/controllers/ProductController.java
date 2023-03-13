package no.ntnu.ProFlex.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Rest controller for products
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class); // TODO Fiks Logger

    /**
     * Product controller constructor.
     */
    public ProductController() {
    }

    /**
     * Returns all the products.
     *
     * @return all products.
     */
    @Operation(summary = "Get all products.", description = "Returns all products.")
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        Iterable<Product> products = this.productRepository.findAll();
        if (!products.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok((List<Product>) products);
    }

    /**
     * Returns the product of a given ID.
     *
     * @param id of the product that want to be return.
     * @return the product of the given ID.
     */
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID.")
    @GetMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> getProductFromAGiveID(
            @Parameter(name = "id", description = "ID of the product to retrieve", required = true, in = ParameterIn.QUERY) @PathVariable int id) {
        Optional<Product> productOptional = this.productRepository.findById(id);
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productRepository.findById(id));
    }

    /**
     * Creates and adds a product to the product list.
     *
     * @param product the product that is getting created.
     * @return returns the response of the http status.
     */
    @Operation(summary = "Creates a product", description = "Creates and adds a product to the product list.")
    @PostMapping("/products")
    public ResponseEntity createProduct(
            @Parameter(name = "product", description = "The product that is created", required = true, in = ParameterIn.PATH) @RequestBody Product product) {
        this.productRepository.save(product);
        Optional<Product> productOptional = this.productRepository.findById(product.getId());
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Update the product for a given ID.
     *
     * @param id      the ID of the product.
     * @param product new product of the product.
     * @return the http status of the operation.
     */
    @Operation(summary = "Update product", description = "Update the product from the product repository")
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> UpdateProduct(
            @Parameter(name = "id", description = "ID of the product to update", required = true, in = ParameterIn.PATH) @PathVariable int id,
            @Parameter(name = "product", description = "The new product that you want the old one to change to", required = true) @PathVariable Product product) {
        Optional<Product> oldProductOptional = productRepository.findById(id);
        if (oldProductOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Product oldProduct = oldProductOptional.get();
        Product updatedProduct = productRepository.save(product);
        if (updatedProduct.equals(oldProduct)) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        } else {
            return ResponseEntity.ok(updatedProduct);
        }
    }

    /**
     * Remove a product for the product list from a given ID.
     *
     * @param id the ID of the product:
     * @return http status of the operation.
     */
    @Operation(summary = "Delete product", description = "Delete a product form the product list form a given ID")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(
            @Parameter(name = "id", description = "ID of the product to delete", required = true) @PathVariable int id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            if (productRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            } else {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
