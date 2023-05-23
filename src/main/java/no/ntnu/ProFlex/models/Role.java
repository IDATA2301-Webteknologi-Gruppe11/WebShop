package no.ntnu.ProFlex.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * This class represent the role of the user
 *
 * @author Ole Kristian
 * @version 1.0
 */
@Schema(description = "Represent the roles that the user can have")
@Entity(name = "roles")
@Table(name = "roles")
public class Role {

    @Schema(description = "the unique id of the role")
    @Id
    @GeneratedValue
    private int rid;

    @Schema(description = "the name of the user")
    @NotNull
    @Column(nullable = false)
    private String rname;

    @OneToMany(mappedBy = "rid")
    private Set<User> uid;

    private static final Logger LOGGER = Logger.getLogger(Role.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    /**
     * Empty constructor that is needed for JPA
     */
    public Role() {

    }

    /**
     * Default constructor for Role
     */
    public Role(String rname) {
        try {
            this.rname = stringChecker(rname, "rname");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Checks if the string is valid.
     * Checks for that the string isn't empty and null.
     * If it is an Illegal Argument Exception is thrown.
     * If it is valid it returns the string.
     * 
     * @param string  that wants to be checked.
     * @param prefiks the name of the string.
     * @return string.
     */
    private String stringChecker(String string, String prefiks) {
        if (string.isEmpty() || string == null) {
            throw new IllegalArgumentException("The string " + "'" + prefiks + "'" + " cant bee empty or null");
        }
        return string;
    }

    /**
     * Returns the role id
     * 
     * @return rid
     */
    public int getRid() {
        return this.rid;
    }

    /**
     * Returns the role name.
     * 
     * @return rname.
     */
    public String getRname() {
        return this.rname;
    }

    /**
     * Returns the users.
     * 
     * @return users.
     */
    public Set<User> getUsers() {
        return this.uid;
    }

    /**
     * sets the role name.
     * 
     * @param rname name for the role.
     */
    public void setRname(String rname) {
        try {
            this.rname = stringChecker(rname, "rname");
        } catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    public Set<User> getUid() {
        return uid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setUid(Set<User> uid) {
        this.uid = uid;
    }

    /**
     * Checks whether this Role object is valid or not.
     * A Role object is considered valid if its name field (rname) is not null or
     * empty.
     * 
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        return !(this.rname == null || this.rname.trim().isEmpty());
    }

}
