package dev.gml.herbarium_backend.facade.encryptions.encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <b>Encryption Facade Interface</b>
 * <p>
 * Defines the contract for encryption operations within the application.
 * This interface provides an abstraction layer for various encryption algorithms,
 * enabling consistent and secure data encryption across the system.
 * </p>
 *
 * <p><b>Purpose:</b></p>
 * <ul>
 *   <li>Abstract encryption operations from business logic</li>
 *   <li>Support multiple encryption algorithms through a unified interface</li>
 *   <li>Provide secure password hashing for storage</li>
 *   <li>Enable easy testing and mocking of encryption operations</li>
 * </ul>
 *
 * <p><b>Supported Algorithms:</b></p>
 * <ul>
 *   <li><b>bcrypt</b> - BCrypt hashing for secure password storage</li>
 *   <li>Extensible for future algorithm support</li>
 * </ul>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 * @see EncryptFacade
 * @see dev.gml.herbarium_backend.facade.encryptions.decrypt.IDecryptFacade
 */
public interface IEncryptFacade {
     /**
     * <b>Encode Data</b>
     * <p>
     * Encodes the provided data using the specified algorithm type.
     * Primarily used for BCrypt password hashing to ensure secure storage.
     * </p>
     *
     * <p><b>Usage Example:</b></p>
     * <pre>
     * {@code
     * String hashedPassword = encryptFacade.encode("bcrypt", "secret123");
     * // Returns BCrypt hashed string like "$2a$10$...")
     * }
     * </pre>
     *
     * @param type The encryption algorithm to use (case-insensitive)
     * @param data The plain data to encode or hash
     * @return the encoded or hashed data as a string
     * @see BCryptPasswordEncoder#encode(CharSequence)
     */
    String encode(String type, String data);
}
