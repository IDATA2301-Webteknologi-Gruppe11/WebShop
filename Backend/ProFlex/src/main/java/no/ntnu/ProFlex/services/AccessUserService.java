package no.ntnu.ProFlex.services;


import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.repository.RoleRepository;
import no.ntnu.ProFlex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * This class provides AccsessUserDetails needed for authentication.
 *
 * @author Ole Kristian
 * @version 1.0
 */
public class AccessUserService implements UserDetailsService {

    private static int MIN_PASSWORD_LENGHT = 9; //TODO høre med gutta.

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);
        if(user.isPresent()) {
            //return new AccessUserService(user.get());
            return null;
        }
        else {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }


}
