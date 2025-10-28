package dev.gml.herbarium_backend.user;

import org.springframework.stereotype.Service;

/**
 * <b>Service Layer for user-related business operations</b>
 * <p>
 * This service handles user management logic and acts as an intermediary
 * between the Controller Layer and the Data Access Layer. It encapsulates
 * business rules and transaction boundaries for user operations.
 * <p>
 * <b>Responsabilities:</b>
 * <ul>
 *      <li>User persistence operations</li>
 *      <li>User retrieval by email</li>
 *      <li>Business logic validation (delegated to other services)</li>
 * </ul>
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see UserEntity
 * @see UserRepository
 * @service indicates this is a Spring service component
 */
@Service
public class UserService {
    /**
     * <b>Repository for user data access operations</b>
     * <p>
     * Injected via constructor dependency injection for better testability
     * and immutability.
     */
    private final UserRepository userRepository;

    /**
     * <b>Constructs a new {@link UserService} with the required dependencies</b>
     * <p>
     * Uses constructor injection to ensure the service is always created
     * with its required dependencies.
     * 
     * @param userRepository The user repository for data access operations.
     * @throws IllegalArgumentException If {@link UserRepository} is {@code null}.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * <b>Saves a user entity to the database</b>
     * <p>
     * Persists the user entity using the underlying repository. This method
     * handles both insert (new user) &amp; update (existing user) operations.
     * 
     * @param user The user entity to be saved.
     * @return The saved user entity with generated ID (if new).
     * @throws IllegalArgumentException If the user parameter is {@code null}.
     * @see UserRepository#save(Object)
     */
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    /**
     * <b>Finds  a user by their email address</b>
     * <p>
     * Searches for a user with the specified email address. If no user is found,
     * throws a {@link RuntimeException} with a descriptive message.
     * 
     * @param email The email address to seach for.
     * @return The found user entity.
     * @throws RuntimeException If no user is found with the given email.
     * @throws IllegalArgumentException If the email parameter is {@code null} or empty.
     * @see UserRepository#findByEmail(String)
     */
    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
