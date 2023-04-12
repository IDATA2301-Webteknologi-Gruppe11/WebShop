package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
