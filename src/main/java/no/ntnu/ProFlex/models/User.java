package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class represent the user of the web site.
 * When a user want's to log in to a site, it is this class that is the user.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@Schema(description = "Represent user that is used to log on to the web site")
@Entity
@Table(name = "user")
public class User {

    @OneToMany(mappedBy = "uid", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @Schema(description = "A unique id of the user.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Schema(description = "The first name of the user.")
    @NotNull
    @Column(nullable = false)
    private String firstName;

    @Schema(description = "The last name of the user.")
    @NotNull
    @Column(nullable = false)
    private String lastName;

    @Schema(description = "The email of the user.")
    @NotNull
    @Column(nullable = false)
    private String email;

    @Schema(description = "The password of the user.")
    @NotNull
    @Column(nullable = false)
    private String pass;

    @Schema(description = "If the user is active")
    @NotNull
    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "rid")
    private Role rid;

    @OneToMany(mappedBy = "uid")
    @JsonManagedReference
    private Set<ShoppingCart> shoppingCarts = new HashSet<>();

    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    /**
     * Constructor for user.
     * This constructor take parameters.
     *
     * @param firstName the first name of the user. //TODO Betre dokumentasjon
     * @param lastName the last name of the user.
     * @param email the email of the user.
     * @param pass the password of the user.
     */
    public User( String firstName, String lastName, String email, String pass) {
        try {
            this.email = stringChecker(email, "Email");
            this.firstName = stringChecker(firstName, "firstName");
            this.lastName = stringChecker(lastName, "lastName");
            this.pass = stringChecker(pass, "pass");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Empty constructor that is needed for JPA
     */
    public User() {

    }

    /**
     * Checks if the string is valid.
     * Checks for that the string isn't empty and null.
     * If it is an Illegal Argument Exception is thrown.
     * If it is valid it returns the string.
     * @param string that wants to be checked.
     * @param prefiks the name of the string.
     * @return string.
     */
    private String stringChecker(String string, String prefiks) {
        if(string.isEmpty() || string == null) {
            throw new IllegalArgumentException("The string " + "'" + prefiks + "'" + " cant be empty or null");
        }
        return string;
    }

    /**
     * Returns the uid.
     * @return uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Returns first name.
     * @return firstName.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns last name
     * @return lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns email.
     * @return email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns password.
     * @return password.
     */
    public String getPass() {
        return this.pass;
    }

    /**
     * Setts the uid
     * @param uid A integer that you want the uid to be.
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Setts the first name.
     * @param firstName A string that you want the firstName to be.
     */
    public void setFirstName(String firstName) {
        try {
            this.firstName = stringChecker(firstName, "firstName");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Setts the last name.
     * @param lastName A string that you want the lastName to be.
     */
    public void setLastName(String lastName) {
        try {
            this.lastName = stringChecker(lastName, "lastName");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Setts the email
     * @param email A string that you want the uname to be.
     */
    public void setEmail(String email) {
        try {
            this.email = stringChecker(email, "email");
        }
        catch (IllegalArgumentException illegalArgumentException){
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Setts the password.
     * @param password A string that you want the uname to be.
     */
    public void setPass(String password) {
        try {
            this.pass = stringChecker(password, "password");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }


    /**
     * Checks if the input variables is valid
     * @return boolean statement, true if valid false if not.
     */
    public boolean isValid() {
        return !"".equals(firstName) && !"".equals(firstName) && !"".equals(pass) && uid >= 0;
    }

    /**
     * Return the state of if the user is active
     * @return boolean statement, true if active false if not.
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Setts the state of the user.
     * @param active boolean statement of the state.
     */
    public void setActive(boolean active) {
        this.active = active;
    }


    public Set<ShoppingCart> getShoppingCarts() {
        return this.shoppingCarts;
    }

    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void setRid(Role rid) {
        this.rid = rid;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Role getRid() {
        return rid;
    }

    /**
     * Checks if the user has a role from a given role name
     * @param roleName the name of the role that you want to check if the user have.
     * @return boolean statement. True if it hase the role, false if it doesn't.
     */
    public boolean hasRole(String roleName) {
        boolean hasRole = false;
        if(getRid().getRname().equals(roleName)) {
            hasRole = true;
        }
        return hasRole;
    }

    public boolean isAdmin() {
        return this.hasRole("ADMIN");
    }
}
