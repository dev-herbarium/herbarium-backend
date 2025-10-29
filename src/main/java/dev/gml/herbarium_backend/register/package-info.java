/**
 * <b>User Registration Package</b>
 * <p>
 * This package handles the complete user registration workflow, including
 * validation, password processing, and user creation with proper role assignment.
 *
 * <p>
 * <b>Package Components:</b>
 * 
 * <ul>
 *   <li>{@link dev.gml.herbarium_backend.register.RegisterController} - Registration API endpoints</li>
 *   <li>{@link dev.gml.herbarium_backend.register.RegisterService} - Registration business logic</li>
 *   <li>{@link dev.gml.herbarium_backend.register.RegisterDTORequest} - Registration request DTO</li>
 *   <li>{@link dev.gml.herbarium_backend.register.RegisterDTOResponse} - Registration response DTO</li>
 * </ul>
 *
 * <p>
 * <b>Registration Workflow:</b>
 * 
 * <ol>
 *   <li>Receive registration request with Base64 encoded password</li>
 *   <li>Validate email format and uniqueness</li>
 *   <li>Verify password match and strength</li>
 *   <li>Decode Base64 password and hash with BCrypt</li>
 *   <li>Create user with default role assignment</li>
 *   <li>Return registration response</li>
 * </ol>
 *
 * <p>
 * <b>Security Features:</b>
 * 
 * <ul>
 *   <li>Base64 password encoding for transmission</li>
 *   <li>BCrypt password hashing for storage</li>
 *   <li>Email validation and duplication checks</li>
 *   <li>Password strength validation</li>
 * </ul>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 * @see dev.gml.herbarium_backend.register.RegisterController
 * @see dev.gml.herbarium_backend.register.RegisterService
 */
package dev.gml.herbarium_backend.register;
