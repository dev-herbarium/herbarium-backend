package dev.gml.herbarium_backend.user;

import java.util.Set;
import dev.gml.herbarium_backend.role.RoleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <b>Represents a user entity in the Herbarium application</b>
 * <p>
 * This entity maps to the "users" table in the database &amp; contains
 * user authentication information, roles, and audit timestamps.
 * <p>
 * <b>Entity Relationships:</b>
 * <ul>
 *      <li>Many-to-Many relationship with RoleEntity</li>
 *      <li>Uses join table "roles_users" for role asignment</li>
 * </ul>
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see RoleEntity
 * @see UserRepository
 * @see UserService
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    /**
     * <b>Unique identifier for the user</b>
     * <p>
     * This is the primary key that auto-generates using database identity strategy.
     * Maps to "id_user" column in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // It should work for MySQL
    @Column(name = "id_user")
    private Long id;

    /**
     * <b>User's email address</b>
     * <p>
     * Must be unique across all users and cannot be {@code null}.
     * Used for user authentication &amp; communication.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * <b>User's encrypted password</b>
     * <p>
     * Stores the BCrypt-hashed password for security.
     * Cannot be {@code null} and should never store plain text passwords.
     */
    @Column(nullable = false)
    private String password;

    /**
     * <b>Set of roles assigned to the user</b>
     * <p>
     * Uses eager fetching to Load roles immediately with the user.
     * Managed through the "roles_users" join table.
     * 
     * @see RoleEntity
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_users",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles;

    /**
     * <b>Timestamp when the user was created</b>
     * <p>
     * Automatically set when the entity is first persisted.
     */
    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt;

    /**
     * <b>Timestamp when the user was last updated</b>
     * <p>
     * Automatically updated whenever the entity is modified.
     */
    @Column(name = "updated_at")
    private java.time.LocalDateTime updatedAt;

    /**
     * <b>JPA lifecycle callback</b>
     * <p>
     * It sets creation &amp; updated timestamps
     * before the entity is persisted for the first time.
     * <p>
     * This method is automatically called by JPA before the initial save.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        updatedAt = java.time.LocalDateTime.now();
    }

    /**
     * <b>JPA lifecycle callback</b>
     * <p>
     * It updates the modification timestamp before the entity is updated.
     * <p>
     * This method is automatically called by JPA before any update operation
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }
}