package no.ntnu.ProFlex.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Schema(description = "A unique id of the user.")
    @Id
    @GeneratedValue
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
    private String password;

    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_WARNING = "Caught Illegal Argument Exception: ";

    /**
     * Constructor for user.
     * This constructor take parameters.
     * @param uid unique id of the user.
     * @param firstName the first name of the user. //TODO Betre dokumentasjon
     * @param lastName the last name of the user.
     * @param email the email of the user.
     * @param password the password of the user.
     */
    public User(int uid, String firstName, String lastName, String email, String password) {
        try {
            this.uid = uid;
            this.email = stringChecker(email, "Email");
            this.firstName = stringChecker(firstName, "firstName");
            this.lastName = stringChecker(lastName, "lastName");
            this.password = stringChecker(password, "Password");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
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
            throw new IllegalArgumentException("The string " + "'" + prefiks + "'" + " cant bee empty or null");
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
    public String getPassword() {
        return this.password;
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
    public void setPassword(String password) {
        try {
            this.password = stringChecker(password, "password");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(ILLEGAL_ARGUMENT_EXCEPTION_WARNING + illegalArgumentException.getMessage());
        }
    }

    public boolean isValid() {
        return !"".equals(firstName) && !"".equals(firstName) && !"".equals(password) && uid > 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
