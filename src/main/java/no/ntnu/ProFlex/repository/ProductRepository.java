package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Category;
import no.ntnu.ProFlex.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository for products
 *
 * @author Ole Kristian
 * @version 1.0
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);
    List<Category> findAllCategoriesByPid(int pid);
    List<Product> findByCategoriesCname(String cname);

    //Query that finds product based on if the name mach or if category matches
    @Query("SELECT p FROM Product p LEFT JOIN p.categories c WHERE " +
            "p.name LIKE CONCAT('%',:query, '%') OR " +
            "p.description LIKE CONCAT('%', :query, '%') OR " +
            "c.cname LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);
}
