package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
