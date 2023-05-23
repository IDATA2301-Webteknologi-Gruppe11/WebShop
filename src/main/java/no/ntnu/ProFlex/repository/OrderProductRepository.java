package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Order;
import org.springframework.data.repository.CrudRepository;
import no.ntnu.ProFlex.models.OrderProduct;

import java.util.List;

public interface OrderProductRepository extends CrudRepository<OrderProduct, Integer> {
}
