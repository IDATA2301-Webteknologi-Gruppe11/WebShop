package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.ShoppingCart;
import no.ntnu.ProFlex.models.User;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
    ShoppingCart findByUser(User user);
}
