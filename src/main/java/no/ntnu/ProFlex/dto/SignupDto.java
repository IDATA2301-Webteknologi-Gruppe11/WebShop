package no.ntnu.ProFlex.dto;


/**
 * Data transfer object (DTO) for data from the sign-up form
 *
 * @author Ole Kristian
 * @version 1.0
 */
public class SignupDto {
    private final String email;
    private final String pass;
    private final String firstName;
    private final String lastName;

    /**
     * Default constructor
     * @param email the email the user uses to log in with
     * @param pass the password the user uses to log in with
     */
    public SignupDto(String firstName, String lastName, String email, String pass) {
        this.email = email;
        this.pass = pass;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Returns password
     * @return password
     */
    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
