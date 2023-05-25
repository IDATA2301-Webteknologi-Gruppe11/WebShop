package no.ntnu.ProFlex.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import no.ntnu.ProFlex.models.Role;
import no.ntnu.ProFlex.services.RoleService;
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
 * @author Håvard Hetland Vestbø
 * @version 1.0
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private static final String JSONEEXCEPTIONMESSAGE = "The Field(s) in the request is missing or is null";
    private static final String SEVERE = "An error occurred: ";
    private static final Logger LOGGER = Logger.getLogger(RoleController.class.getName());

    /**
     * Returns all the roles.
     *
     * @return all roles.
     */
    @Operation(summary = "Get all roles", description = "Returns all the roles")
    @GetMapping("/getAll")
    public ResponseEntity<List<Role>> getRole() {
        Iterable<Role> roles = this.roleService.getAll();
        if (!roles.iterator().hasNext()) {
            return new ResponseEntity("Didn't find products", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<Role>) roles);
    }

    /**
     * Returns the roles of a given ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user of the given ID
     */
    @Operation(summary = "Return a reol", description = "Find and return a role from id")
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleFromId(
            @Parameter(name = "id", description = "the id of the role that you want to find", required = true) @PathVariable int id) {
        Role role = this.roleService.findById(id);
        if (role == null) {
            return new ResponseEntity("Didn't find role", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(role);
    }

    /**
     * Creates and adds a role.
     *
     * @param role the role that is getting created
     * @return a ResponseEntity with an HTTP status indicating the success or
     *         failure of the operation
     * @exception JSONException if an error occurs while creating the product
     */
    @Operation(summary = "add role", description = "add a new role to the role repository and return the http status.")
    @PostMapping("/add")
    public ResponseEntity<Role> createUser(
            @Parameter(name = "role", description = "The role that is created", required = true) @RequestBody Role role) {
        try {
            if (!this.roleService.add(role)) {
                return new ResponseEntity("Role was not added", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Role was added", HttpStatus.CREATED);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update the role for a given ID.
     *
     * @param id   the ID of the role to update
     * @param role new user of the user
     * @return a ResponseEntity with an HTTP status indicating the success or
     *         failure of the operation
     * @exception JSONException if an error occurs while updating the product
     */
    @Operation(summary = "update role", description = "update a existing role in the role repository and return http status")
    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateUser(
            @Parameter(name = "id", description = "id of the role that you want to update", required = true) @PathVariable int id,
            @Parameter(name = "user", description = "the role that the exsisting role you want to be updated to", required = true) @PathVariable Role role) {
        try {
            Role oldRole = this.roleService.findById(id);
            if (oldRole == null) {
                return new ResponseEntity("didn't find role", HttpStatus.NOT_FOUND);
            }
            this.roleService.update(id, role);
            if (this.roleService.findById(id) == null) {
                return new ResponseEntity("Role didn't update", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Role was updated", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a role from the role list with the given ID.
     *
     * @param id the ID of the role to delete
     * @return a ResponseEntity with an HTTP status indicating the success or
     *         failure of the operation
     * @exception JSONException if an error occurs while deleting the product
     */
    @Operation(summary = "Delete role", description = "Delete a role form the role list form given ID")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Role> deleteRole(
            @Parameter(name = "id", description = "ID of the roles to delete", required = true) @PathVariable int id) {
        try {
            if (!this.roleService.delete(id)) {
                return new ResponseEntity("Role was not removed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Role was removed", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

}
