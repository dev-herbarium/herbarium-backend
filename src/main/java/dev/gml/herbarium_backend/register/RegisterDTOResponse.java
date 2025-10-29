package dev.gml.herbarium_backend.register;

import lombok.Builder;

/**
 * <b>Registration Data Transfer Object (Response)</b>
 * <p>
 * Represents the standardized response structure for registration operations.
 * This record provides consistent response formatting for both successful
 * registrations and validation errors.
 *
 * <p>
 * <b>Response Scenarios:</b>
 * 
 * <ul>
 *   <li><b>Success:</b> Contains user ID, email, and success message</li>
 *   <li><b>Error:</b> Contains error message, submitted email, and null user ID</li>
 * </ul>
 * 
 * <p>
 * <b>Note: </b>
 * The {@code @Builder} annotation enables 
 * fluent object creation using the builder pattern.
 * </p>
 *
 * @param message Descriptive message indicating success or failure
 * @param email The email address from the registration attempt
 * @param userId The generated user ID for successful registrations, null for failures
 * @author gml
 * @version 1.0
 * @since 2025
 * @see RegisterDTORequest
 * @see RegisterController
 */
@Builder
public record RegisterDTOResponse(
    /**
     * Descriptive message indicating the result of the registration attempt.
     * For success: "User registered successfully"
     * For errors: "Registration failed: [specific error message]"
     */
    String message,

    /**
     * The email address that was attempted to be registered.
     * Present in both success and error responses for reference.
     */
    String email,

    /**
     * The system-generated unique identifier for the user.
     * Only populated for successful registrations, null for failures.
     */
    Long userId
) {}
