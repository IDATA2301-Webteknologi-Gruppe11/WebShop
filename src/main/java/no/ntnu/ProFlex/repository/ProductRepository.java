package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for products
 *
 * @author Ole Kristian
 * @version 1.0
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
