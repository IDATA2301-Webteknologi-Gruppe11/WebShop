package no.ntnu.ProFlex.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Schema(description = "A unique id for the user.")
    @Id
    @GeneratedValue
    private int uid;

    @Schema(description = "The username for the user.")
    private String uname;

    @Schema(description = "The email for the user.")
    private String email;

    @Schema(description = "The password for the user.")
    private String password;

    private static final Logger LOGGER = Logger.getLogger(User.class.getName());
    private static final String LOGGER_STRING_WARNING = "Caught Illegal Argument Exception: ";

    /**
     * Constructor for user.
     * This constructor take parameters.
     * @param uid unique id for the user.
     * @param uname the username for the user.
     * @param email the email for the user.
     * @param password the password for the user.
     */
    public User(int uid, String uname, String email, String password) {
        try {
            this.uid = uid;
            this.uname = stringChecker(uname, "Uname");
            this.email = stringChecker(email, "Email");
            this.password = stringChecker(password, "Password");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(LOGGER_STRING_WARNING + illegalArgumentException.getMessage());
        }
    }

    /**
     * Checks if the string is valid.
     * Checks for that the string isn't empty and null.
     * @param string that wants to be checked.
     * @param prefiks the name of the string.
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
        return uid;
    }

    /**
     * Returns username.
     * @return username.
     */
    public String getUname() {
        return uname;
    }

    /**
     * Returns email.
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns password.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    //TODO sjekke om man skal kunne sette ny id.
    /**
     * Setts the uid
     * @param uid A integer that you want the uid to be.
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * Setts the uname
     * @param uname A string that you want the uname to be.
     */
    public void setUname(String uname) {
        try {
            this.uname = stringChecker(uname, "uname");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            LOGGER.warning(LOGGER_STRING_WARNING + illegalArgumentException.getMessage());
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
            LOGGER.warning(LOGGER_STRING_WARNING + illegalArgumentException.getMessage());
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
            LOGGER.warning(LOGGER_STRING_WARNING + illegalArgumentException.getMessage());
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
