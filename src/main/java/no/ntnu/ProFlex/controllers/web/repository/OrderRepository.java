package no.ntnu.ProFlex.controllers.web.repository;

import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends CrudRepository<Order, Integer> {
    //Set<Order> findAllByUsers(Set<User> users);
}
