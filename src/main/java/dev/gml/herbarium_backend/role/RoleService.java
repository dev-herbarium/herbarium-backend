package dev.gml.herbarium_backend.role;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

/**
 * <b>Service Layer for role-related business operations</b>
 * <p>
 * This service handles role management logic and provides methods for
 * role assigment and retrieval. It encapsulates the business rules
 * around role management and acts as an intermediary between the
 * application logic and data access layer.
 * <p>
 * <b>Key Responsibilities:</b>
 * <ul>
 *      <li>Role retrieval by identifier</li>
 *      <li>Default role assigment for new users</li>
 *      <li>Business logic encapsulation for role operations</li>
 * </ul>
 * <p>
 * <b>Important Note:</b>
 * The default role assignment currently uses a hardcoded ID (2L) which
 * assumes specific data setup in the database. This dependency should
 * be documented and managed carefully.
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see RoleEntity
 * @see RoleRepository
 * @service indicates this is a Spring service component
 */
@Service
public class RoleService {

    /**
     * <b>Repository for role data access operations</b>
     * <p>
     * Injected via constructor dependency injection for better testability
     * and immutability.
     */
    private final RoleRepository repository;

    /**
     * <b>Constructs a new RoleService with the required dependencies</b>
     * <p>
     * Uses constructor injection to ensure the service is always created
     * with its required dependencies.
     * 
     * @param repository The role repository for data access operations
     * @throws IllegalArgumentException if repository is {@code null}
     */
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    /**
     * <b>Retrieves a role by its unique identifier</b>
     * <p>
     * Finds a role entity using the provided ID. If no role is found with
     * the given ID, throws a {@link RuntimeException}
     * 
     * @param id The unique identifier of the role to retrieve
     * @return The found role entity
     * @throws RuntimeException if no role is found with the given ID
     * @throws IllegalArgumentException if the {@code id} parameter is {@code null}
     * @see RoleRepository#findById(Object)
     */
    public RoleEntity getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    /**
     * <b>Assigns the default role to a new user</b>
     * <p>
     * Returns a set containing the default user role (ROLE_USER) which
     * is assigned to all new users during registration. Currently uses
     * a hardcoded ID (2L) to identify the default role.
     * <p>
     * <b>Important Implementation Detail:</b>
     * This method assumes that role ID 2L corresponds to "ROLE_USER" in
     * the database. This dependency should be consistent with the data
     * initialization scripts (data.sql or data-h2.sql).
     * 
     * @return A set containing the default role (ROLE_USER)
     * @throws RuntimeException if the default role (ID 2L) is not found
     * @see #getById(Long)
     */
    public Set<RoleEntity> assignDefaultRole() {
        RoleEntity defaultRole = this.getById(2L); // ROLE_USER

        Set<RoleEntity> roles = new HashSet<>();

        roles.add(defaultRole);

        return roles;
    }
}
