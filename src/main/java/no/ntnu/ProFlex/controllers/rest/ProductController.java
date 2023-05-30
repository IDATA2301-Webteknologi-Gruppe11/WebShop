package no.ntnu.ProFlex.controllers.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@RestController
@RequestMapping("/api/product")
@Api(value = "Product API", tags = "Product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    @ApiOperation(value = "Get all products", notes = "Returns all the products")
    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getProducts() {
        Iterable<Product> products = this.productService.getAll();
        if (!products.iterator().hasNext()) {
            return new ResponseEntity("Didn't find products", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<Product>) products);
    }

    @ApiOperation(value = "Get product by ID", notes = "Retrieves a product by its ID")
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

    @GetMapping("/name/{name}")
    public ResponseEntity getByName(
            @Parameter(name = "name", description = "name of the product you want to find")
            @PathVariable String name) {
        Product product = this.productService.findByName(name);
        if(product == null) {
            return new ResponseEntity("Didnt find product", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }

    @ApiOperation(value = "Get products by category", notes = "Retrieves products by category")
    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        List<Product> products = this.productService.getByCategory(category);
        if (products == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }

    @ApiOperation(value = "Add a product", notes = "Creates and adds a product to the product list")
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @Parameter(name = "product", description = "The product to be created", required = true)
            @RequestBody Product product) {
        try {
            if (!this.productService.add(product)) {
                return new ResponseEntity("Product was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Product was added", HttpStatus.CREATED);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity<>(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the product for a given ID.
     *
     * @param id      the ID of the product to update
     * @param product new product of the product
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @throws JSONException if an error occurs while updating the product
     */
    @ApiOperation(value = "Update product", notes = "Update the product from the product repository")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(
            @Parameter(name = "id", description = "ID of the product to update", required = true)
            @PathVariable int id,
            @Parameter(name = "product", description = "The new product that you want the old one to change to", required = true)
            @RequestBody Product product) {
        try {
            Product oldProduct = this.productService.findById(id);
            if (oldProduct == null) {
                return new ResponseEntity<>("Didn't find product", HttpStatus.NOT_FOUND);
            }
            this.productService.update(id, product);
            if (this.productService.findById(id) == null) {
                return new ResponseEntity<>("Product didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>("Product was updated", HttpStatus.NO_CONTENT);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity<>(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a product from the product list with the given ID.
     *
     * @param id the ID of the product to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @throws JSONException if an error occurs while deleting the product
     */
    @ApiOperation(value = "Delete product", notes = "Delete a product from the product list given its ID")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Product> deleteProduct(
            @Parameter(name = "id", description = "ID of the product to delete", required = true)
            @PathVariable int id) {
        try {
            if (!this.productService.delete(id)) {
                return new ResponseEntity("Product was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Product was removed", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
