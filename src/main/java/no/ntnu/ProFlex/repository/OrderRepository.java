package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.models.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Set;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    //Set<Order> findAllByUsers(Set<User> users);
}
