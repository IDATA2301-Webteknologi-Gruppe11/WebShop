package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.CartItem;
import org.springframework.data.repository.CrudRepository;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
}
