package no.ntnu.ProFlex.Controllers;

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
 *
 * @author Ole Kristian Dvergsdal                  //TODO Høre med girtz om klassen er bra?
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
     */
    @Operation(summary = "Get all product", description = "Returns all the products")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        Iterable<Product> products = this.productService.getAll();
        if(!products.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
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
    public ResponseEntity<Product> getProductFromAGiveID(
            @Parameter(name = "id", description = "ID of the product to retrieve", required = true, in = ParameterIn.QUERY)
            @PathVariable int id) {
        Product product = this.productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(product);
        }
    }

    /**
     * Creates and adds a product to the product list.
     *
     * @param product the product that is getting created.
     * @return returns the response of the http status.
     */
    @Operation(summary = "Creates a product", description = "Creates and adds a product to the product list.")
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(
            @Parameter(name = "product", description = "The product that is created", required = true, in = ParameterIn.PATH)
            @RequestBody Product product) {
        if(!this.productService.add(product)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            return ResponseEntity.ok(product);
        }
    }

    /**
     * Update the product for a given ID.
     *
     * @param id the ID of the product.
     * @param product new product of the product.
     * @return the http status of the operation.
     */
    @Operation(summary = "Update product", description = "Update the product from the product repository")
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> UpdateProduct(
            @Parameter(name = "id", description = "ID of the product to update", required = true, in = ParameterIn.PATH)
            @PathVariable int id,
            @Parameter(name = "product", description = "The new product that you want the old one to change to", required = true)
            @PathVariable Product product) {
        Product oldProduct = this.productService.findById(id);
        if (oldProduct == null) {
            return ResponseEntity.notFound().build();
        }
        this.productService.update(id, product);//TODO exception/errors
        if (this.productService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } else {
            return ResponseEntity.ok(product);
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
        public ResponseEntity<Product> deleteProduct (
        @Parameter(name = "id", description = "ID of the product to delete", required = true)
        @PathVariable int id){
            if (!this.productService.delete(id)) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            } else {
                return ResponseEntity.ok().build();
            }
        }
}
