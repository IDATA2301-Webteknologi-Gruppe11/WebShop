package no.ntnu.ProFlex.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Rest controller for the products
 * //TODO høre med girtz om man burde a http og hente jeson data ifrå http body for å få tak i produck?
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Returns all the products.
     *
     * @return all products
     * @throws Exception if an error occurs while retrieving the products
     */
    @Operation(summary = "Get all product", description = "Returns all the products")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        try {
            Iterable<Product> products = this.productService.getAll();
            if (!products.iterator().hasNext()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok((List<Product>) products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Returns the product of a given ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product of the given ID
     * @throws Exception if an error occurs while retrieving the product
     */
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID.")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductFromAGiveID(
            @Parameter(name = "id", description = "ID of the product to retrieve", required = true, in = ParameterIn.QUERY) @PathVariable int id) {
        try {
            Product product = this.productService.findById(id);
            if (product == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Creates and adds a product.
     *
     * @param product the product that is getting created
     * @return returns the response of the http status
     * @throws Exception if an error occurs while creating the product
     */
    @Operation(summary = "Creates a product", description = "Creates and adds a product to the product list.")
    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(
            @Parameter(name = "product", description = "The product that is created", required = true, in = ParameterIn.PATH) @RequestBody Product product) {
        try {
            this.productService.add(product);
            if (!this.productService.add(product)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return new ResponseEntity("Field(s) missing or null in request", HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Update the product for a given ID.
     *
     * @param id the ID of the product to update
     * @param product new product of the product
     * @return the http status of the operation
     * @throws Exception if an error occurs while updating the product
     */
    @Operation(summary = "Update product", description = "Update the product from the product repository")
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(
            @Parameter(name = "id", description = "ID of the product to update", required = true, in = ParameterIn.PATH) @PathVariable int id,
            @Parameter(name = "product", description = "The new product that you want the old one to change to", required = true) @PathVariable Product product) {
        try {
            Product oldProduct = this.productService.findById(id);
            if (oldProduct == null) {
                return ResponseEntity.notFound().build();
            }
            this.productService.update(id, product);
            Product updatedProduct = this.productService.findById(id);
            if (updatedProduct == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return new ResponseEntity("Field(s) missing or null in request", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a product from the product list with the given ID.
     *
     * @param id the ID of the product to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception  if an error occurs while deleting the product
     */
    @Operation(summary = "Delete product", description = "Delete a product from the product list given its ID")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(
            @Parameter(name = "id", description = "ID of the product to delete", required = true) @PathVariable int id) {
        try {
            if (!this.productService.delete(id)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity("Field(s) missing or null in request", HttpStatus.BAD_REQUEST);
        }
    }
}
