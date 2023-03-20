package no.ntnu.ProFlex.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Represent controller class for user.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Return all users.
     * Also, http status. OK if it works, NOT FOUND if nothing is found.
     *
     * @return All users, http status.
     */
    @Operation(summary = "Get all users", description = "Find and return all users form the user repository and return http status")
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser() {
        Iterable<User> users = this.userService.getAll();
        if(!users.iterator().hasNext()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok((List<User>) users);
    }

    /**
     * Find and return a user form ID.
     * Also, https status. OK if it works, NOT FOUND if nothing is found.
     *
     * @param id the id of the user that you want to return
     * @return A user, http status.
     */
    @Operation(summary = "Return a user", description = "Find and return a user from id")
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserFromId(
            @Parameter(name = "id", description = "the id of the user that you want to find")
            @PathVariable int id) {
        User user = this.userService.findById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Adds a user.
     * Also return, https status. OK of it worked, BAD REQUEST if somthing is wrong with the user.
     *
     * @param user the user that you want to add.
     * @return user, http status.
     */
    @Operation(summary = "add user", description = "add a new user to the user repository and return the http status.")
    @PostMapping("/user")
    public ResponseEntity<User> createUser(User user) {
        if(!this.userService.add(user)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else {
            return ResponseEntity.ok(user);
        }
    }

    /**
     * Update an exsisting user and return it.
     * Also return the http status. OK if it worked, NOT FOUND if the id does not exsists and INTERNAL SERVER ERROR if it exists but didnt work.
     *
     * @param id the id of the user that you want to update.
     * @param user the user you want the existing on to be updated to.
     * @return updated user, http status
     */
    @Operation(summary = "update user", description = "update a existing user in the user repository and return http status")
    @PutMapping("/user")
    public ResponseEntity<User> updateUser(
            @Parameter(name = "id", description = "id of the user that you want to update")
            @PathVariable int id,
            @Parameter(name = "user", description = "the user that the exsisting user you want to be updated to")
            @PathVariable User user) {
        User oldUser = this.userService.findById(id);
        if(oldUser == null) {
            return ResponseEntity.notFound().build();
        }
        this.userService.update(id, user);
        if(this.userService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        else {
            return ResponseEntity.ok(user);
        }
    }

    /**
     * Removes a user.
     * Returns the https status. OK of it worked, INTERNAL SERVER ERROR if it did not work.
     *
     * @param id of the user that you want to remove.
     * @return https status.
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(int id) {
        if(!this.userService.delete(id)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        else {
            return ResponseEntity.ok().build();
        }
    }


}
