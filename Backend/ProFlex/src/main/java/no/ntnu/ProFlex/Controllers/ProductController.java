package no.ntnu.ProFlex.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import no.ntnu.ProFlex.Products.Product;
import no.ntnu.ProFlex.Products.ProductList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Rest controller for the products
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@RestController
public class ProductController {

    //Data
    private ProductList productList;

    //Controller
    private ProFlexController proFlexController;

    /**
     * Starts the Product Controller.
     */
    public ProductController() {
        initialize();
    }

    /**
     * Initialize the product controller.
     */
    private void initialize() {
        this.productList = new ProductList();
    }

    /**
     * Sets the productList to the product list in the main controller class ProFlexController
     * @param productList the product list form the main product class.
     */
    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    public void setProFlexController(ProFlexController proFlexController) {
        this.proFlexController = proFlexController;
    }

    /**
     * Returns all the products.
     * @return all products
     */
    @Operation(summary = "Get all product", description = "Returns all the products")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        if(this.productList.getProducts().isEmpty() || this.productList == null) {
            return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND); //TODO do we need to respond with not found if there is no objects in the prduct list?
        }
        return new ResponseEntity<List<Product>>(this.productList.getProducts(), HttpStatus.OK);
    }

    /**
     * Returns the product of a given ID.
     * @param id of the product that want to be return.
     * @return the product of the given ID.
     */
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID.")
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductFromAGiveID(
            @Parameter(name = "id", description = "ID of the product to retrieve", required = true, in = ParameterIn.QUERY)
            @PathVariable int id) {
        if (this.productList.getProductFromAGivenID(id) == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(this.productList.getProductFromAGivenID(id), HttpStatus.OK);
    }

    /**
     * Creates and adds a product to the product list.
     * @param product the product that is getting created.
     * @return returns the response of the http status.
     */
    @Operation(summary = "Creates a product", description = "Creates and add a product to the production. The return the status of the http.")
    @PostMapping("/Products")
    public ResponseEntity<Object> createProduct(
            @Parameter(name = "product", description = "The product that is created", required = true, in = ParameterIn.QUERY)
            @RequestBody Product product) {
        this.productList.addProduct(product);
        if(this.productList.getProducts().get(this.productList.getProducts().size() - 1).getId() == product.getId()) {
            return new ResponseEntity(product,HttpStatus.CREATED);
        }
        return new ResponseEntity("Product wasn't created", HttpStatus.BAD_REQUEST);
    }
}
