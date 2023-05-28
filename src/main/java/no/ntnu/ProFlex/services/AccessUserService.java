package no.ntnu.ProFlex.services;

/**
 * This class provides AccsessUserDetails needed for authentication.
 *
 * @author Ole Kristian
 * @version 1.0
 */

import no.ntnu.ProFlex.dto.UserProfileDto;
import no.ntnu.ProFlex.models.Role;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.repository.RoleRepository;
import no.ntnu.ProFlex.repository.UserRepository;
import no.ntnu.ProFlex.security.AccessUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Provides AccessUserDetails needed for authentication
 */
@Service
public class AccessUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final String MIN_ONE_CAPITALLETTER = ".*[A-Z]+.*";
    private static final String MIN_ONE_SMALLLETTER = ".*[a-z]+.*";
    private static final String MIN_ONE_NUMBER = ".*\\d+.*";
    private static final String CORRECT_EMAIL_REQUIREMENTS =  "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return new AccessUserDetails(user.get());
        } else {
            throw new UsernameNotFoundException("User " + email + "not found");
        }
    }

    /**
     * Get the user which is authenticated for the current session
     *
     * @return User object or null if no user has logged in
     */
    public User getSessionUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String email = authentication.getName();
        return this.userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Check if user with given email exists in the database
     *
     * @param email Email of the user to check, case-sensitive
     * @return True if user exists, false otherwise
     */
    private boolean userExists(String email) {
        try {
            loadUserByUsername(email);
            return true;
        } catch (UsernameNotFoundException ex) {
            return false;
        }
    }

    /**
     * Try to create a new user
     *
     * @param firstName first name of the new user
     * @param lastName last name of the new user
     * @param email the email user uses to log in
     * @param password password for the new user
     * @return null when user created, error message on error
     */
    public String tryCreateNewUser(String firstName, String lastName, String email, String password) {
        String errorMessage;
        if(firstName == null || firstName.trim().isEmpty()) {
            return  "First name can't be empty";
        }
        if(lastName == null || lastName.trim().isEmpty()) {
            return "Last name can't be empty";
        }
        errorMessage = checkEmailRequirements(email);
        if(errorMessage != null) {
            return errorMessage;
        }
        errorMessage = checkPasswordRequirements(password);
        if(errorMessage == null) {
            createUser(firstName, lastName, email, password);
        }
        return errorMessage;
    }

    /**
     * Check if email matches the requirements
     *
     * @param email a email to check
     * @return null of all ok, error message on error
     */
    private String checkEmailRequirements(String email) {
        String errorMessage = null;
        if(email == null || email.isEmpty()) {
            errorMessage = "Email can't be empty";
        }
        else if (userExists(email)) {
            errorMessage = "Email already taken";
        }
        else if (!email.matches(CORRECT_EMAIL_REQUIREMENTS)) {
            errorMessage = "Email requirements not fulfilled";
        }
        return errorMessage;
    }

    /**
     * Check if password matches the requirements
     *
     * @param password A password to check
     * @return null if all OK, error message on error
     */
    private String checkPasswordRequirements(String password) {
        String errorMessage = null;
        if (password == null || password.isEmpty()) {
            errorMessage = "Password can't be empty";
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            errorMessage = "Password must be at least " + MIN_PASSWORD_LENGTH + " characters";
        }
        else if(!password.matches(MIN_ONE_CAPITALLETTER) || !password.matches(MIN_ONE_SMALLLETTER) || !password.matches(MIN_ONE_NUMBER)) {
            errorMessage = "Password must contain at least one capital letter, one small letter and on number";
        }
        return errorMessage;
    }


    /**
     * Create a new user in the database
     *
     * @param email Username of the new user
     * @param password Plaintext password of the new user
     */
    private void createUser(String firstName, String lastName, String email, String password) {
        Role userRole = roleRepository.findOneByName("USER");
        if (userRole != null) {
            User user = new User(firstName, lastName, email, createHash(password));
            user.setRole(userRole);
            userRepository.save(user);
        }
    }

    /**
     * Create a secure hash of a password
     *
     * @param password Plaintext password
     * @return BCrypt hash, with random salt
     */
    private String createHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Update the user information
     *
     * @param user the user that you want to update
     * @param profileDto the new information you want for the user
     * @return true
     */
    public boolean updateProfile(User user, UserProfileDto profileDto) {
        user.setFirstName(profileDto.getFirstName());
        user.setLastName(profileDto.getLastName());
        return true;
    }
}
