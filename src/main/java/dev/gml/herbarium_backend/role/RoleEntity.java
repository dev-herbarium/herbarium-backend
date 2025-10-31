package dev.gml.herbarium_backend.role;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.gml.herbarium_backend.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * <b>Represents a Role Entity in the Herbarium application</b>
 * <p>
 * This entity maps to the "roles" table in the database and defines
 * the security roles that can be assigned to users. Roles determine
 * what actions users are authorized to perform within the system.
 * <p>
 * <b>Entity Relationships:</b>
 * <ul>
 *      <li>Many-to-Many relationship with {@link UserEntity} (inverse side)</li>
 *      <li>Bidirectional relationship managed by {@link UserEntity}</li>
 * </ul>
 * <p>
 * <b>Common Role Examples:</b>
 * <ul>
 *      <li>ROLE_ADMIN - Full system access</li>
 *      <li>ROLE_USER - Standard user privileges</li>
 * </ul>
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see UserEntity
 * @see RoleService
 * @see RoleRepository
 */
@Entity
@Table(name = "roles")
@Data
public class RoleEntity {
    
    /**
     * <b>Unique identifier for the role</b>
     * <p>
     * This is the primary key that auto-generates using database identity strategy.
     * Maps to "id_role" column in the database.
     */
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // It should work for MySQL
    @Column(name = "id_role")
    private Long id;
    
    /**
     * <b>Name of the role</b>
     * <p>
     * Typically follows the convention "ROLE_*" (e.g., "ROLE_USER", "ROLE_ADMIN").
     * Used for role-based authorization checks throughout the application.
     */
    private String name;

    /**
     * <b>Set of users assigned to this role</b>
     * <p>
     * Represents the inverse side of the Many-to-Many relationship with {@link UserEntity}.
     * This collection is ignored during JSON serialization to prevent circular references.
     * 
     * @see UserEntity
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users;
}
