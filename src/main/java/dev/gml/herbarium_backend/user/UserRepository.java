package dev.gml.herbarium_backend.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <b>Repository Interface for {@link UserEntity} data access operations</b>
 * <p>
 * This interface extends {@link JpaRepository} and provides custom query methods
 * for user-related database operations. Spring Data JPA automatically implements
 * this interface at runtime.
 * <p>
 * <b>Supported Operations:</b>
 * <ul>
 *      <li>Standard CRUD operations (inherited from {@link JpaRepository})</li>
 *      <li>Custom query methods for user-specific operations</li>
 *      <li>Email-based user lookup</li>
 * </ul>
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see UserEntity
 * @see JpaRepository
 * @see UserService
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * <b>Finds a user by their email address</b>
     * <p>
     * This method performs a case-sensitive search for a user with the exact
     * email address provided. Returns an {@link Optional} that will be empty
     * if no user is found with the given mail.
     * 
     * @param email The email address to search for (case-sensitive)
     * @return an {@link Optional} containing the found user, or empty if not found
     * @throws IllegalArgumentException If the {@code email} parameter is null
     */
    Optional<UserEntity> findByEmail(String email);
    
    //Optional<UserEntity> findByUsername(String username); // Use for backward compatibility
    
}
