package dev.gml.herbarium_backend.facade.encryptions.encrypt;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <b>Encryption Facade Implementation</b>
 * <p>
 * Concrete implementation of {@link IEncryptFacade} that provides
 * actual encryption operations for supported algorithms. This component
 * handles secure password hashing using BCrypt with automatic salting.
 * </p>
 *
 * <p><b>Supported Operations:</b></p>
 * <ul>
 *   <li><b>BCrypt Hashing</b> - Secure password hashing with salt</li>
 *   <li><b>Fallback Support</b> - Returns data as-is for unsupported types</li>
 * </ul>
 *
 * <p><b>Security Features:</b></p>
 * <ul>
 *   <li>Automatic salt generation for each password</li>
 *   <li>Configurable work factor for hashing complexity</li>
 *   <li>Resistance to brute-force and rainbow table attacks</li>
 *   <li>Industry-standard password security</li>
 * </ul>
 * 
 * <p><b>Note:</b> The {@code @Component} annotation indicates this is a
 * Spring component for dependency injection.</p>
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see IEncryptFacade
 * @see BCryptPasswordEncoder
 */
@Component
public class EncryptFacade implements IEncryptFacade {

    /**
     * <b>BCrypt Password Encoder Instance</b>
     * <p>
     * Thread-safe encoder that provides BCrypt hashing functionality.
     * Automatically generates unique salts for each password and uses
     * a configurable work factor for hashing complexity.
     * </p>
     *
     * @see BCryptPasswordEncoder
     */
    private final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    /**
     * <b>Encode Data Using Specified Algorithm</b>
     * <p>
     * Implements the encoding operation based on the specified algorithm type.
     * Primarily used for BCrypt password hashing to ensure passwords are
     * never stored in plain text.
     * </p>
     *
     * <p><b>Algorithm Support:</b></p>
     * <ul>
     *   <li><b>"bcrypt"</b> - Uses {@link BCryptPasswordEncoder} for secure hashing</li>
     *   <li><b>Other types</b> - Returns data unchanged as fallback</li>
     * </ul>
     *
     * <p><b>BCrypt Characteristics:</b></p>
     * <ul>
     *   <li>Each call generates a different hash due to unique salts</li>
     *   <li>Hashes start with {@code $2a$} prefix</li>
     *   <li>Automatically handles salt generation and storage</li>
     *   <li>Provides {@code matches()} method for verification</li>
     * </ul>
     *
     * @param type The encryption algorithm to use (case-insensitive)
     * @param data The plain data to encode or hash
     * @return the encoded or hashed data as a string, or original data for unsupported types
     * @see BCryptPasswordEncoder#encode(CharSequence)
     */
    @Override
    public String encode(String type, String data) {
        if ("bcrypt".equalsIgnoreCase(type)) {
            return bcryptEncoder.encode(data);
        }
        // For other types of fallback, return "as-is":
        return data;
    }
}
