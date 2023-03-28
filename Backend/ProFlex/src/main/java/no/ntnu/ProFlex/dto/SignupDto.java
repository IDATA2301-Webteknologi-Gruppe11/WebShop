package no.ntnu.ProFlex.dto;

/**
 * Data transfer object (DTO) for data from the sign-up form
 *
 * @author Ole Kristian
 * @version 1.0
 */
public class SignupDto {
    private final String username;
    private final String password;

    /**
     * Default constructor
     * @param username
     * @param password
     */
    public SignupDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns password
     * @return password
     */
    public String getPassword() {
        return password;
    }
}
