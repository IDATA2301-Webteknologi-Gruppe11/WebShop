package no.ntnu.ProFlex.dto;

/**
 * Data transfer object (DTO) for submitting changes to user profile data
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
public class UserProfileDto {

    private final String firstName;
    private final String lastName;
    private final String password;

    /**
     * Default Constructor.
     *
     * @param firstName
     * @param lastName
     * @param password
     */
    public UserProfileDto(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    /**
     * Returns first name.
     * @return firstName.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns last name.
     * @return lastName.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns password.
     * @return password.
     */
    public String getPassword() {
        return this.password;
    }
}
