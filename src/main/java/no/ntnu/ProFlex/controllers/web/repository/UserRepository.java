package no.ntnu.ProFlex.controllers.web.repository;

import no.ntnu.ProFlex.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {
   Optional<User> findByEmail(String email);
}
