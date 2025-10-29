package dev.gml.herbarium_backend.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * <b>Registration Data Transfer Object (Request)</b>
 * <p>
 * Represents the data structure for user registration requests.
 * This record validates incoming registration data using Jakarta Bean Validation
 * annotations and provides a method for password confirmation validation.
 *
 * <p>
 * <b>Validation Rules:</b>
 * 
 * <ul>
 *   <li>Email must be non-blank and valid format</li>
 *   <li>Password must be non-blank and at least 6 characters</li>
 *   <li>Password confirmation must be non-blank</li>
 *   <li>Passwords must match (validated by passwordsMatch method)</li>
 * </ul>
 *
 * @param email The user's email address (must be unique and valid)
 * @param password The user's password (Base64 encoded, minimum 6 characters after decoding)
 * @param confirmPassword Password confirmation for validation
 * @author gml
 * @version 1.0
 * @since 2025
 * @see RegisterDTOResponse
 * @see RegisterService
 */
public record RegisterDTORequest(
    /**
     * User's email address for registration and authentication.
     * Must be a valid email format and unique across the system.
     */
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid Email Format!")
    String email,

    /**
     * User's password for account authentication.
     * Must be Base64 encoded when sent to the API and will be
     * decoded and hashed with BCrypt before storage.
     */
    @NotBlank(message = "Password is required!")
    @Size(min = 6, message = "The password is too short. It must be at least six characters!")
    String password,

    /**
     * Password confirmation field to ensure the user entered
     * their intended password correctly.
     */
    @NotBlank(message = "Password confirmation is required!")
    String confirmPassword
    
) {
    /**
     * <b>Validate Password Match</b>
     * <p>
     * Checks if the password and confirmation password fields match.
     * This method is used during registration validation to ensure
     * the user entered their intended password correctly.
     *
     * @return true if passwords are both non-null and equal, false otherwise
     */
    public boolean passwordsMatch() {
            return password != null && password.equals(confirmPassword);
    }
}
