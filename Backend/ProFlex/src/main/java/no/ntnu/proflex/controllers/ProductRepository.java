package no.ntnu.proflex.controllers;

import no.ntnu.proflex.products.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for products
 *
 * @author Ole Kristian
 * @version 1.0
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
