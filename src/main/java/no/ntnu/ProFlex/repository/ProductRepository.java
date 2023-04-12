package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Repository for products
 *
 * @author Ole Kristian
 * @version 1.0
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);
    //List<Category> findAllCategoriesByPid(int pid);
}
