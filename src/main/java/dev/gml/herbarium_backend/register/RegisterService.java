package dev.gml.herbarium_backend.register;

import org.springframework.stereotype.Service;

import dev.gml.herbarium_backend.facade.encryptions.decrypt.IDecryptFacade;
import dev.gml.herbarium_backend.facade.encryptions.encrypt.IEncryptFacade;
import dev.gml.herbarium_backend.role.RoleService;
import dev.gml.herbarium_backend.user.UserEntity;
import dev.gml.herbarium_backend.user.UserRepository;
import dev.gml.herbarium_backend.user.UserService;

/**
 * <b>Registration Service</b>
 * <p>
 * Core service layer responsible for user registration business logic.
 * This service orchestrates the complete registration workflow including
 * validation, password processing, and user creation with proper role assignment.
 *
 * <p>
 * <b>Registration Workflow:</b>
 * 
 * <ol>
 *   <li>Validate email uniqueness and format</li>
 *   <li>Verify password match and strength</li>
 *   <li>Decode Base64 password and hash with BCrypt</li>
 *   <li>Assign default user role</li>
 *   <li>Persist user to database</li>
 * </ol>
 *
 * <p>
 * <b>Dependencies:</b>
 * 
 * <ul>
 *   <li>UserService - User persistence operations</li>
 *   <li>UserRepository - Email uniqueness checks</li>
 *   <li>RoleService - Default role assignment</li>
 *   <li>Encryption Facades - Password processing</li>
 * </ul>
 * 
 * <p><b>Note:</b> The {@code @Service} annotation indicates this is a
 * Spring service component.</p>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 * @see UserEntity
 * @see RegisterDTORequest
 */
@Service
public class RegisterService {
    
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final IDecryptFacade decryptFacade;
    private final IEncryptFacade encryptFacade;
    
    /**
     * Constructs a new RegisterService with all required dependencies.
     *
     * @param userService Service for user persistence operations
     * @param userRepository Repository for email uniqueness checks
     * @param roleService Service for role assignment
     * @param decryptFacade Facade for password decoding
     * @param encryptFacade Facade for password encryption
     */
    public RegisterService(UserService userService, UserRepository userRepository, RoleService roleService, IDecryptFacade decryptFacade, IEncryptFacade encryptFacade) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.decryptFacade = decryptFacade;
        this.encryptFacade = encryptFacade;
    }

    /**
     * <b>Register New User</b>
     * <p>
     * Executes the complete user registration process with validation and
     * security measures. This is the core method that orchestrates the
     * entire registration workflow.
     *
     * <p>
     * <b>Validation Steps:</b>
     * 
     * <ol>
     *   <li>Check email uniqueness in database</li>
     *   <li>Validate email format using RegEx</li>
     *   <li>Verify password fields match</li>
     *   <li>Decode Base64 password and validate length</li>
     *   <li>Hash password with BCrypt before storage</li>
     * </ol>
     *
     * @param dto The registration data containing user credentials
     * @return the created UserEntity with generated ID and assigned roles
     * @throws IllegalArgumentException for various validation failures:
     *         <ul>
     *           <li>"Email already registered" - duplicate email</li>
     *           <li>"Email should be valid" - invalid email format</li>
     *           <li>"Passwords do not match" - password confirmation failure</li>
     *           <li>"Password must be at least 6 characters" - short password</li>
     *           <li>"Invalid password encoding!" - malformed Base64</li>
     *         </ul>
     * @see RegisterDTORequest
     * @see UserEntity
     */
    public UserEntity registerUser(RegisterDTORequest dto) {
        // Check if email already exists
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Validate email format:
        if (!isValidEmail(dto.email())) {
            throw new IllegalArgumentException("Email should be valid");
        }
        
        // Validate passwords match
        if (!dto.passwordsMatch()) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        
        // Validate password strength (after decoding)
        try {
            // Decode "base64" password first
            String passwordDecoded = decryptFacade.decode("base64", dto.password());
            
            // Then validate the actual password length
            if (passwordDecoded.length() < 6) {
                throw new IllegalArgumentException("Password must be at least 6 characters");
            }

            // Encrypt password with bcrypt
            String hashedPassword = encryptFacade.encode("bcrypt", passwordDecoded);

            UserEntity newUser = UserEntity.builder()
                .email(dto.email())
                .password(hashedPassword)
                .roles(roleService.assignDefaultRole())
                .build();
    
            return userService.save(newUser);

        } catch (IllegalArgumentException error) {
            // Re-throw with appropiate message:
            if (error.getMessage().contains("Invalid Base64")) {
                throw new IllegalArgumentException("Invalid password encoding!");
            }
            throw error;
        }
    }

    /**
     * <b>Validate Email Format</b>
     * <p>
     * Performs basic email format validation using a regular expression.
     * This provides an additional validation layer beyond the Jakarta
     * Validation annotations in the DTO.
     * </p>
     *
     * @param email The email address to validate
     * @return true if the email matches the expected format, false otherwise
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }
}
