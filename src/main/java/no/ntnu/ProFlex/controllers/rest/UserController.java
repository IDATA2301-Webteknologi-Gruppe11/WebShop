package no.ntnu.ProFlex.controllers.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.User;
import no.ntnu.ProFlex.services.UserService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Rest controller for the user.
 *
 * @author IDATA2306 Group 11
 * @version 1.0
 */
@RestController
@RequestMapping("/api/user")
@Api(value = "User API", tags = "User")
public class UserController {

    @Autowired
    private UserService userService;

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(ProductController.class.getName());

    /**
     * Returns all the users.
     *
     * @return all users.
     */
    @ApiOperation(value = "Get all users", notes = "Returns all the user")
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUser() {
        Iterable<User> users = this.userService.getAll();
        if (!users.iterator().hasNext()) {
            return new ResponseEntity("Didn't find user", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<User>) users);
    }

    /**
     * Returns the user of a given ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user of the given ID
     */
    @ApiOperation(value = "Return a user", notes = "Find and return a user from id")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserFromId(
            @Parameter(name = "id", description = "the id of the user that you want to find", required = true)
            @PathVariable int id) {
        User user = this.userService.findById(id);
        if (user == null) {
            return new ResponseEntity("Didn't find user", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email Email of the user to retrieve.
     * @return ResponseEntity containing the user if found, or an error message with HTTP status code if the user is not found.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(
            @Parameter(name = "email", description = "email of the user that you want to find")
            @PathVariable String email) {
        User user = this.userService.findByEmail(email);
        if(user == null) {
            return new ResponseEntity("Didnt find user", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Creates and adds a user.
     *
     * @param user the user that is getting created
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while creating the product
     */
    @ApiOperation(value = "add user", notes = "add a new user to the user repository and return the http status.")
    @PostMapping("/add")
    public ResponseEntity<User> createUser(
            @Parameter(name = "user", description = "The user that is created", required = true)
            @RequestBody User user) {
        try {
            if (!this.userService.add(user)) {
                return new ResponseEntity("User was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("User was added", HttpStatus.CREATED);
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the user for a given ID.
     *
     * @param id the ID of the user to update
     * @param user new user of the user
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException if an error occurs while updating the product
     */
    @ApiOperation(value = "update user", notes = "update a existing user in the user repository and return http status")
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(
            @Parameter(name = "id", description = "id of the user that you want to update", required = true)
            @PathVariable int id,
            @Parameter(name = "user", description = "the user that the exsisting user you want to be updated to", required = true)
            @RequestBody User user) {
        try {
            User oldUser = this.userService.findById(id);
            if (oldUser == null) {
                return new ResponseEntity("didn't find user", HttpStatus.NOT_FOUND);
            }
            this.userService.update(id, user);
            if (this.userService.findById(id) == null) {
                return new ResponseEntity("User didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("User was updated", HttpStatus.OK);
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a user from the user list with the given ID.
     *
     * @param id the ID of the user to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @exception JSONException  if an error occurs while deleting the product
     */
    @ApiOperation(value = "Delete user", notes = "Delete a user form the user list form given ID")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<User> deleteUser(
            @Parameter(name = "id", description = "UD of the user to delete", required = true)
            @PathVariable int id) {
        try {
            if (!this.userService.delete(id)) {
                return new ResponseEntity("User was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("User was removed", HttpStatus.OK);
        }
        catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
