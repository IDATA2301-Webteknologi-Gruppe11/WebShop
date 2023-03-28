package no.ntnu.ProFlex.dto;

/**
 * Data transfer object (DTO) for submitting changes to user profile data
 */
public class UserProfileDto {
    private final String bio;

    /**
     * Default Constructor
     *
     * @param bio
     */
    public UserProfileDto(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }
}
