package no.ntnu.ProFlex.repositories;

import no.ntnu.ProFlex.models.Product;
import no.ntnu.ProFlex.services.ProductService;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for products
 *
 * @author Ole Kristian
 * @version 1.0
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Page<Product> findAll(Pageable pageable);

}
