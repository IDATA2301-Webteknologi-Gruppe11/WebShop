package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Represent the service class for user.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Returns all user for the user repository.
     *
     * @return users.
     */
    public Iterable<User> getAll() {
        return this.userRepository.findAll();
    }

    /**
     * Find and return a user for a given id.
     *
     * @param id of the user that you want to find
     * @return the user
     */
    public User findById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    /**
     * FInd user by email
     *
     * @param email email of the user that you want to find.
     * @return the user
     */
    public User findByEmail(String email) {
       return this.userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Adds a user to the user repository.
     *
     * @param user that you want to add.
     * @return A boolean statement. True if the user is added, false is not.
     */
    public boolean add(User user) {
        boolean added = false;
        if(canBeAdded(user)) {
            this.userRepository.save(user);
            added = true;
        }
        return added;
    }

    /**
     * Checks if a user can be added to the user repository.
     *
     * @param user the user that you want to check.
     * @return A boolean statement. True if can be added, false if not.
     */
    private boolean canBeAdded(User user) {
        return user != null && user.isValid();
    }

    /**
     * Remove user form the user repository.
     *
     * @param id the id of the user that you want to remove.
     * @return A boolean statement. True if user is removed, false if not.
     */
    public boolean delete(int id) {
        boolean deleted = false;
        if(findById(id) != null) {
            this.userRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update an existing user from in the user repository.
     *
     * @param id of the user that you want to update
     * @param user new user that you want the user to be update to.
     */
    public void update(int id, User user) {
        User existingUser = findById(id);
        String errorMessage = null;
        if (existingUser == null) {
            errorMessage = "No user exists with the id " + id;
        }
        if (user == null || !user.isValid()) {
            errorMessage = "Wrong data in request body";
        }
        else if(user.getUid() != id) {
            errorMessage = "The ID of the user in the URL does not match anny ID in the JSON data";
        }
        if (errorMessage == null) {
            this.userRepository.save(user);
        }
    }
}
