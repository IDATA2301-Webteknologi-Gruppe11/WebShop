package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.CartItem;
import no.ntnu.ProFlex.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    List<CartItem> findAllByShoppingCart(ShoppingCart shoppingCart);
}
