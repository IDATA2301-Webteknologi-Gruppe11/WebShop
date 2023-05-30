package no.ntnu.ProFlex.services;

import no.ntnu.ProFlex.models.Role;
import no.ntnu.ProFlex.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Rest controller for the role.
 *
 * @author Håvard Hetland Vestbø
 * @version 1.0
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * Returns all roles for the role repository.
     *
     * @return roles.
     */
    public Iterable<Role> getAll() {
        return this.roleRepository.findAll();
    }

    /**
     * Find and return a role for a given id.
     *
     * @param id of the role that you want to find
     * @return the role
     */
    public Role findById(int id) {
        return this.roleRepository.findById(id).orElse(null);
    }

    public Role findByRoleName(String name) {
        return this.roleRepository.findOneByName(name);
    }

    /**
     * Adds a user to the role repository.
     *
     * @param role that you want to add.
     * @return A boolean statement. True if the user is added, false is not.
     */
    public boolean add(Role role) {
        boolean added = false;
        if (canBeAdded(role)) {
            this.roleRepository.save(role);
            added = true;
        }
        return added;
    }

    /**
     * Checks if a role can be added to the user repository.
     *
     * @param role the role that you want to check.
     * @return A boolean statement. True if can be added, false if not.
     */
    private boolean canBeAdded(Role role) {
        return role != null && role.isValid();
    }

    /**
     * Remove role form the role repository.
     *
     * @param id the id of the role that you want to remove.
     * @return A boolean statement. True if role is removed, false if not.
     */
    public boolean delete(int id) {
        boolean deleted = false;
        if (findById(id) != null) {
            this.roleRepository.deleteById(id);
            deleted = true;
        }
        return deleted;
    }

    /**
     * Update an existing role from in the user repository.
     *
     * @param id   of the role that you want to update
     * @param role new role that you want the role to be update to.
     */
    public void update(int id, Role role) {
        Role existingRole = findById(id);
        String errorMessage = null;
        if (existingRole == null) {
            errorMessage = "No role exists with the id " + id;
        }
        if (role == null || !role.isValid()) {
            errorMessage = "Wrong data in request body";
        } else if (role.getId() != id) {
            errorMessage = "The ID of the role in the URL does not match anny ID in the JSON data";
        }
        if (errorMessage == null) {
            this.roleRepository.save(role);
        }
    }
}
