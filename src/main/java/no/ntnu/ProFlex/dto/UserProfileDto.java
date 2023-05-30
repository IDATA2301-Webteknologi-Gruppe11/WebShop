package no.ntnu.ProFlex.dto;

import no.ntnu.ProFlex.models.Role;

/**
 * Data transfer object (DTO) for submitting changes to user profile data
 *
 * @author Ole Kristian Dvergsdal
 * @version 1.0
 */
public class UserProfileDto {

    private final String firstName;
    private final String lastName;

    /**
     * Default Constructor for changing user profile.
     *
     * @param firstName
     * @param lastName
     */
    public UserProfileDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
}
