package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Order;
import no.ntnu.ProFlex.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
