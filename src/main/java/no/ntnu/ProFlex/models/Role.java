package no.ntnu.ProFlex.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

/**
 * This class represents the role of a user.
 * It is used to define the roles that users can have.
 * Each role is associated with one or more users.
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@ApiModel(description = "Represent the roles that the user can have")
@Entity(name = "roles")
@Table(name = "roles")
public class Role {

    @ApiModelProperty(value = "The unique ID of the role")
    @Id
    @GeneratedValue
    private int id;

    @ApiModelProperty(value = "The name of the role")
    @NotNull
    @Column(nullable = false)
    private String name;

    @ApiModelProperty(value = "a set of user that have this role")
    @OneToMany(mappedBy = "role")
    @JsonManagedReference(value = "user-role")
    private Set<User> users;

    /**
     * Empty constructor that is needed for JPA.
     */
    public Role() {

    }

    /**
     * Constructs a Role object with the given name.
     *
     * @param name the name of the role
     */
    public Role(String name) {
        this.name = stringChecker(name, "name");
    }

    /**
     * Checks if the string is valid.
     * Checks that the string is not empty or null.
     * If it is empty or null, an IllegalArgumentException is thrown.
     * If it is valid, it returns the string.
     *
     * @param string  the string to be checked
     * @param prefix  the name of the string
     * @return the valid string
     */
    private String stringChecker(String string, String prefix) {
        if (string.trim().isEmpty() || string == null) {
            throw new IllegalArgumentException("The string '" + prefix + "' cannot be empty or null.");
        }
        return string;
    }

    /**
     * Retrieves the ID of the role.
     *
     * @return the ID of the role
     */
    public int getId() {
        return this.id;
    }

    /**
     * Retrieves the name of the role.
     *
     * @return the name of the role
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name for the role.
     *
     * @param name the name for the role
     */
    public void setName(String name) {
        this.name = stringChecker(name, "rname");
    }

    /**
     * Retrieves the set of users associated with the role.
     *
     * @return the set of users associated with the role
     */
    public Set<User> getUsers() {
        return this.users;
    }

    /**
     * Sets the ID of the role.
     *
     * @param id the ID of the role
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the set of users associated with the role.
     *
     * @param users the set of users to associate with the role
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Checks whether this Role object is valid.
     * A Role object is considered valid if its name field is not null or empty.
     *
     * @return true if the Role is valid, false otherwise
     */
    public boolean isValid() {
        return !(this.name == null || this.name.trim().isEmpty());
    }
}