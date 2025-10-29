package dev.gml.herbarium_backend.facade.encryptions.decrypt;

import java.util.Base64;

/**
 * <b>Decryption Facade Interface</b>
 * <p>
 * Defines the contract for decryption operations within the application.
 * This interface provides an abstraction layer for various decoding algorithms,
 * allowing for consistent decryption handling across the system.
 * </p>
 *
 * <p><b>Purpose:</b></p>
 * <ul>
 *   <li>Abstract decoding operations from business logic</li>
 *   <li>Support multiple decoding algorithms through a unified interface</li>
 *   <li>Provide consistent error handling for decoding failures</li>
 *   <li>Enable easy testing and mocking of decoding operations</li>
 * </ul>
 *
 * <p><b>Supported Algorithms:</b></p>
 * <ul>
 *   <li><b>base64</b> - Base64 decoding for transmitted passwords</li>
 *   <li>Extensible for future algorithm support</li>
 * </ul>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 * @see DecryptFacade
 * @see dev.gml.herbarium_backend.facade.encryptions.encrypt.IEncryptFacade
 */
public interface IDecryptFacade {
    /**
     * <b>Decode Data</b>
     * <p>
     * Decodes the provided data using the specified algorithm type.
     * Currently supports Base64 decoding for password processing.
     * </p>
     *
     * <p><b>Usage Example:</b></p>
     * <pre>
     * {@code
     * String decodedPassword = decryptFacade.decode("base64", "c2VjcmV0MTIz");
     * // Returns "secret123"
     * }
     * </pre>
     *
     * @param type The decoding algorithm to use (case-insensitive)
     * @param data The encoded data to decode
     * @return the decoded data as a string
     * @throws IllegalArgumentException if the data cannot be decoded using the specified algorithm,
     *         particularly for invalid Base64 encoding
     * @see Base64#getDecoder()
     */
    String decode(String type, String data);
}
