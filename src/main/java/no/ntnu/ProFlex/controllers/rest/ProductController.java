package no.ntnu.ProFlex.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;
import org.json.JSONException;

//TODO høre med girz om id er det beste for å fjerne product/oppdatere product

/**
 * Rest controller for the products
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());



    /**
     * Returns all the products.
     *
     * @return all products
     */
    @Operation(summary = "Get all product", description = "Returns all the products")
    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getProducts() {
        Iterable<Product> products = this.productService.getAll();
        if (!products.iterator().hasNext()) {
            return new ResponseEntity("Didn't find products", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<Product>) products);
    }

    /**
     * Returns the product of a given ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product of the given ID
     */
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductFromAGiveID(
            @Parameter(name = "id", description = "ID of the product to retrieve", required = true)
            @PathVariable int id) {
        Product product = this.productService.findById(id);
        if (product == null) {
            return new ResponseEntity("Didn't find product", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }


    /**
     * Creates and adds a product.
     *
     * @param product the product that is getting created
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while creating the product
     */
    @Operation(summary = "Adds a product", description = "Creates and adds a product to the product list.")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @Parameter(name = "product", description = "The product that is created", required = true)
            @RequestBody Product product) {
        try {
            if (!this.productService.add(product)) {
                return new ResponseEntity("Product was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Product was added", HttpStatus.CREATED);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    //TODO HttpEntity<String> http høre med girtz om ditta e betre.

    /**
     * Update the product for a given ID.
     *
     * @param id the ID of the product to update
     * @param product new product of the product
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while updating the product
     */
    @Operation(summary = "Update product", description = "Update the product from the product repository")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(
            @Parameter(name = "id", description = "ID of the product to update", required = true)
            @PathVariable int id,
            @Parameter(name = "product", description = "The new product that you want the old one to change to", required = true)
            @PathVariable Product product) {
        try {
            Product oldProduct = this.productService.findById(id);
            if (oldProduct == null) {
                return new ResponseEntity("didn't find product", HttpStatus.NOT_FOUND);
            }
            this.productService.update(id, product);
            if (this.productService.findById(id) == null) {
                return new ResponseEntity("Product didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Product was updated", HttpStatus.NO_CONTENT); //TODO høre med girtz
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a product from the product list with the given ID.
     *
     * @param id the ID of the product to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException  if an error occurs while deleting the product
     */
    @Operation(summary = "Delete product", description = "Delete a product from the product list given its ID")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Product> deleteProduct(
            @Parameter(name = "id", description = "ID of the product to delete", required = true)
            @PathVariable int id) {
        try {
            if (!this.productService.delete(id)) {
                return new ResponseEntity("Product was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("product was removed", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query) {
        return ResponseEntity.ok(productService.searchProducts(query));
    }
}