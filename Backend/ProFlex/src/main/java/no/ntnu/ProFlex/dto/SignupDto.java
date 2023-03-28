package no.ntnu.ProFlex.dto;

/**
 * Data transfer object (DTO) for data from the sign-up form
 *
 * @author Ole Kristian
 * @version 1.0
 */
public class SignupDto {
    private final String email;
    private final String password;

    /**
     * Default constructor
     * @param email the email the user uses to log in with
     * @param password the password the user uses to log in with
     */
    public SignupDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Returns email
     * @return email
     */
    public String getUsername() {
        return email;
    }

    /**
     * Returns password
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
