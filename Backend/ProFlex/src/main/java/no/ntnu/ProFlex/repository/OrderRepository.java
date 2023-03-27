package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findAllByUid(int uid);
}
