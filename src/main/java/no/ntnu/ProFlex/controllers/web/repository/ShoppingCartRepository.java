package no.ntnu.ProFlex.controllers.web.repository;

import no.ntnu.ProFlex.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
}
