package no.ntnu.ProFlex.repository;

import no.ntnu.ProFlex.models.Role;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findOneByRname(String rname);
}
