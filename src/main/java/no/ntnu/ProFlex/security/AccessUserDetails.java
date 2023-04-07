package no.ntnu.ProFlex.security;

import io.swagger.v3.oas.annotations.media.Schema;
import no.ntnu.ProFlex.models.Role;
import no.ntnu.ProFlex.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Holds the authentication information that is needed for the UserDetailsService.
 *
 * @author Ole Kristian
 * @version 1.0
 */

public class AccessUserDetails implements UserDetails {

    @Schema(description = "email that the user uses to log in whit.")
    private final String email;
    @Schema(description = "the password user uses to log in whit")
    private final String password;
    @Schema(description = "if the user is active or not")
    private final boolean isActive;
    @Schema(description = "The authorities")
    private final Set<GrantedAuthority> authorities = new HashSet<>();

    /**
     * Default constructor.
     * @param user the user that you want to set access user details of
     */
    public AccessUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPass();
        this.isActive = user.isActive();
    }

    /**
     * Converts the roles in the Set of roles.
     * @param roles a set of roles that you want to convert.
//     */
    private void convertRoles(Set<Role> roles) {
        this.authorities.clear();
        for(Role role: roles) {
            this.authorities.add(new SimpleGrantedAuthority(role.getRname()));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
