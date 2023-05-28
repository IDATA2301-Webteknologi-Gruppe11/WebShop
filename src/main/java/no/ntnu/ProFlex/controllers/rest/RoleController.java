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
 * Rest controller for managing roles.
 *
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
     * Returns all roles.
     *
     * @return a list of all roles
     */
    @Operation(summary = "Get all roles", description = "Returns a list of all roles")
    @GetMapping("/getAll")
    public ResponseEntity<List<Role>> getRoles() {
        Iterable<Role> roles = this.roleService.getAll();
        if (!roles.iterator().hasNext()) {
            return new ResponseEntity("No roles found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok((List<Role>) roles);
    }

    /**
     * Returns the role with the given ID.
     *
     * @param id the ID of the role to retrieve
     * @return the role with the given ID
     */
    @Operation(summary = "Get role by ID", description = "Returns the role with the specified ID")
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleFromId(
            @Parameter(name = "id", description = "The ID of the role to retrieve", required = true)
            @PathVariable int id) {
        Role role = this.roleService.findById(id);
        if (role == null) {
            return new ResponseEntity("Role not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(role);
    }

    /**
     * Creates and adds a new role.
     *
     * @param role the role to add
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @throws JSONException if an error occurs while creating the role
     */
    @Operation(summary = "Add role", description = "Creates and adds a new role to the role repository")
    @PostMapping("/add")
    public ResponseEntity<Role> createRole(
            @Parameter(name = "role", description = "The role to add", required = true)
            @RequestBody Role role) {
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
     * Updates the role with the given ID.
     *
     * @param id   the ID of the role to update
     * @param role the new role data
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @throws JSONException if an error occurs while updating the role
     */
    @Operation(summary = "Update role", description = "Updates an existing role in the role repository and returns the HTTP status")
    @PutMapping("/update/{id}")
    public ResponseEntity<Role> updateRole(
            @Parameter(name = "id", description = "ID of the role to update", required = true)
            @PathVariable int id,
            @Parameter(name = "role", description = "The new role data", required = true)
            @RequestBody Role role) {
        try {
            Role oldRole = this.roleService.findById(id);
            if (oldRole == null) {
                return new ResponseEntity("Role not found", HttpStatus.NOT_FOUND);
            }
            this.roleService.update(id, role);
            if (this.roleService.findById(id) == null) {
                return new ResponseEntity("Role not updated", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Role updated successfully", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes a role with the given ID.
     *
     * @param id the ID of the role to delete
     * @return a ResponseEntity with an HTTP status indicating the success or failure of the operation
     * @throws JSONException if an error occurs while deleting the role
     */
    @Operation(summary = "Delete role", description = "Deletes a role from the role list with the given ID")
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Role> deleteRole(
            @Parameter(name = "id", description = "ID of the role to delete", required = true)
            @PathVariable int id) {
        try {
            if (!this.roleService.delete(id)) {
                return new ResponseEntity("Role not removed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity("Role removed successfully", HttpStatus.OK);
        } catch (JSONException e) {
            LOGGER.severe(SEVERE + e.getMessage());
            return new ResponseEntity(JSONEEXCEPTIONMESSAGE, HttpStatus.BAD_REQUEST);
        }
    }
}
