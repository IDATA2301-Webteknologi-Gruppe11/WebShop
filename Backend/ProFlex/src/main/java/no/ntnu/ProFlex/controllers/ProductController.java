package no.ntnu.ProFlex.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ProductRepository productRepository;

    //Controller
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class); //TODO Fiks Logger

    /**
     * Product Controller Constructor.
     */
    public ProductController() {
        Product product = new Product("sadf", 23, 324, "sfsd");
        Product produc2 = new Product("sadsadfaf", 234, 3243, "sfs213d");
        productRepository.save(produc2);
        productRepository.save(product);
    }


    /**
     * Returns all the products.
     * @return all products
     */
    @Operation(summary = "Get all product", description = "Returns all the products")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        Iterable<Product> products = this.productRepository.findAll();
        if(!products.iterator().hasNext()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>((List<Product>) products, HttpStatus.OK);
    }
    
//    /**
//     * Returns the product of a given ID.
//     * @param id of the product that want to be return.
//     * @return the product of the given ID.
//     */
//    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID.")
//    @GetMapping("/products/{id}")
//    public ResponseEntity<Product> getProductFromAGiveID(
//            @Parameter(name = "id", description = "ID of the product to retrieve", required = true, in = ParameterIn.QUERY)
//            @PathVariable int id) {
//        if (this.productList.getProductFromAGivenID(id) == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return new ResponseEntity<Product>(this.productList.getProductFromAGivenID(id), HttpStatus.OK);
//    }
//
//    /**
//     * Creates and adds a product to the product list.
//     * @param product the product that is getting created.
//     * @return returns the response of the http status.
//     */
//    @Operation(summary = "Creates a product", description = "Creates and adds a product to the product list.")
//    @PostMapping("/products")
//    public ResponseEntity createProduct(
//            @Parameter(name = "product", description = "The product that is created", required = true, in = ParameterIn.PATH)
//            @RequestBody Product product) {
//        this.productList.addProduct(product);
//        if(this.productList.checkIfIdIsInTheProductList(product.getId())) {
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
//
//    /**
//     * Update the name of a product form a given ID.
//     * @param id the ID of the product.
//     * @param product new name of the product.
//     * @return the http status of the operation.
//     */
//    @Operation(summary = "Update product name", description = "Update the name of a given product in the product list")
//    @PutMapping("/products/{id}")
//    public ResponseEntity<Product> UpdateProduct(
//            @Parameter(name = "id", description = "ID of the product to update", required = true, in = ParameterIn.PATH)
//            @PathVariable int id,
//            @Parameter(name = "product", description = "The new product that you want the old one to change to", required = true)
//            @PathVariable Product product) {
//        Product oldProduct = this.productList.getProductFromAGivenID(id);
//        if(productList.checkIfIdIsInTheProductList(id)) {
//            this.productList.getProductFromAGivenID(id).setProduct(product);
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        if(oldProduct.equals(this.getProductFromAGiveID(id))) {
//            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
//        }
//        else {
//            return ResponseEntity.ok(product);
//        }
//    }
//
//    /**
//     * Remove a product for the product list from a given ID.
//     * @param id the ID of the product:
//     * @return http status of the operation.
//     */
//    @Operation(summary = "Delete product", description = "Delete a product form the product list form a given ID")
//    @DeleteMapping("/products/{id}")
//    public ResponseEntity<Product> deleteProduct(
//            @Parameter(name = "id", description = "ID of the product to delete", required = true)
//            @PathVariable int id
//    ) {
//        if(this.productList.checkIfIdIsInTheProductList(id)) {
//            this.productList.deleteProduct(id);
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        if(!this.productList.checkIfIdIsInTheProductList(id)) {
//            return ResponseEntity.status(HttpStatus.OK).build();
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //TODO fin ut kva som er riktig HttpStatus når eit object ikkje blir sletta
//        }
//    }
}
