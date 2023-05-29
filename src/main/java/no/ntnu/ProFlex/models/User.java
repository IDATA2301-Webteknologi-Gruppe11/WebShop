package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.*;

/**
 * This class represent the user of the web site.
 * When a user want's to log in to a site, it is this class that is the user.
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
@ApiModel(description = "Represent user that is used to log on to the web site")
@Entity
@Table(name = "user")
public class User {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-order")
    @ApiModelProperty(value = "a set of order this user have")
    private Set<Order> orders = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "the id of the user")
    private int id;

    @NotNull
    @Column(nullable = false)
    @ApiModelProperty(value = "the first name of the user")
    private String firstName;

    @NotNull
    @Column(nullable = false)
    @ApiModelProperty(value = "the last name of the user")
    private String lastName;

    @NotNull
    @Column(nullable = false)
    @ApiModelProperty(value = "the email of the user")
    private String email;

    @NotNull
    @Column(nullable = false)
    @ApiModelProperty(value = "The password of the user.")
    private String pass;

    @NotNull
    @Column(nullable = false)
    @ApiModelProperty(value = "If the user is active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "role")
    @JsonBackReference(value = "user-role")
    @ApiModelProperty(value = "the role the user have")
    private Role role;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference(value = "user-shoppingcart")
    @ApiModelProperty(value = "the shopping cart for the user")
    private ShoppingCart shoppingCart;

    @ApiModelProperty(value = "token to reset password")
    private String resetPasswordToke;

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
            this.email = stringChecker(email, "Email");
            this.firstName = stringChecker(firstName, "firstName");
            this.lastName = stringChecker(lastName, "lastName");
            this.pass = stringChecker(pass, "pass");
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
        if(string == null || string.trim().isEmpty()) {
            throw new IllegalArgumentException("The string " + "'" + prefiks + "'" + " cant be empty or null");
        }
        return string;
    }

    /**
     * Returns the uid.
     * @return uid.
     */
    public int getId() {
        return this.id;
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
     * @param id A integer that you want the uid to be.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setts the first name.
     * @param firstName A string that you want the firstName to be.
     */
    public void setFirstName(String firstName) {
        this.firstName = stringChecker(firstName, "firstName");
    }

    /**
     * Setts the last name.
     * @param lastName A string that you want the lastName to be.
     */
    public void setLastName(String lastName) {
        this.lastName = stringChecker(lastName, "lastName");
    }

    /**
     * Setts the email
     * @param email A string that you want the uname to be.
     */
    public void setEmail(String email) {
        this.email = stringChecker(email, "email");
    }

    /**
     * Setts the password.
     * @param password A string that you want the uname to be.
     */
    public void setPass(String password) {
        this.pass = stringChecker(password, "password");
    }


    /**
     * Checks if the input variables is valid
     * @return boolean statement, true if valid false if not.
     */
    public boolean isValid() {
        return !" ".equals(firstName) && !" ".equals(lastName) && !" ".equals(email) && !" ".equals(pass);
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

    /**
     * Retrieves the shopping cart associated with the user.
     *
     * @return the shopping cart of the user
     */
    public ShoppingCart getShoppingCart() {
        return this.shoppingCart;
    }

    /**
     * Sets the shopping cart for the user.
     *
     * @param shoppingCart the shopping cart to be set for the user
     */
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Sets the orders for the user.
     *
     * @param orders the set of orders to be assigned to the user
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    /**
     * Sets the role for the user.
     *
     * @param role the role to be assigned to the user
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Retrieves the set of orders associated with the user.
     *
     * @return the set of orders belonging to the user
     */
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Retrieves the role of the user.
     *
     * @return the role of the user
     */
    public Role getRole() {
        return role;
    }

    public String getResetPasswordToke() {
        return resetPasswordToke;
    }

    public void setResetPasswordToke(String resetPasswordToke) {
        this.resetPasswordToke = resetPasswordToke;
    }

    /**
     * Checks if the user has a role from a given role name
     * @param roleName the name of the role that you want to check if the user have.
     * @return boolean statement. True if it hase the role, false if it doesn't.
     */
    public boolean hasRole(String roleName) {
        boolean hasRole = false;
        if(getRole().getName().equals(roleName)) {
            hasRole = true;
        }
        return hasRole;
    }

    /**
     * Checks if the user has the role of an administrator.
     *
     * @return true if the user has the "ADMIN" role, false otherwise
     */
    public boolean isAdmin() {
        return this.hasRole("ADMIN");
    }
}
