package dev.gml.herbarium_backend.facade.encryptions.decrypt;

import org.springframework.stereotype.Component;
import java.util.Base64;

/**
 * <b>Decryption Facade Implementation</b>
 * <p>
 * Concrete implementation of {@link IDecryptFacade} that provides
 * actual decoding operations for supported algorithms. This component
 * handles the decoding of transmitted data, particularly passwords
 * encoded in Base64 format.
 * </p>
 *
 * <p><b>Supported Operations:</b></p>
 * <ul>
 *   <li><b>Base64 Decoding</b> - Decodes Base64 encoded strings</li>
 *   <li><b>Fallback Support</b> - Returns data as-is for unsupported types</li>
 * </ul>
 *
 * <p><b>Security Context:</b></p>
 * <ul>
 *   <li>Used in user registration to decode Base64 encoded passwords</li>
 *   <li>Provides the first step in the password processing pipeline</li>
 *   <li>Ensures proper handling of malformed encoded data</li>
 * </ul>
 * 
 * <p><b>Note:</b> The {@code @Component} annotation indicates this is a
 * Spring component for dependency injection.</p>
 *
 * @author gml
 * @version 1.0
 * @since 2025
 * @see IDecryptFacade
 * @see Base64
 */
@Component
public class DecryptFacade implements IDecryptFacade {
    
    /**
     * <b>Decode Data Using Specified Algorithm</b>
     * <p>
     * Implements the decoding operation based on the specified algorithm type.
     * Currently supports Base64 decoding with proper error handling for
     * malformed encoded data.
     * </p>
     *
     * <p><b>Algorithm Support:</b></p>
     * <ul>
     *   <li><b>"base64"</b> - Uses {@link Base64#getDecoder()} for decoding</li>
     *   <li><b>Other types</b> - Returns data unchanged as fallback</li>
     * </ul>
     *
     * <p><b>Error Handling:</b></p>
     * <ul>
     *   <li>Throws {@link IllegalArgumentException} for invalid Base64 data</li>
     *   <li>Provides descriptive error messages for debugging</li>
     *   <li>Maintains application stability through proper exception handling</li>
     * </ul>
     *
     * @param type The decoding algorithm to use (case-insensitive)
     * @param data The encoded data to decode
     * @return the decoded data as a string, or original data for unsupported types
     * @throws IllegalArgumentException if Base64 decoding fails due to invalid encoding
     * @see Base64.Decoder#decode(String)
     */
    @Override
    public String decode(String type, String data) {
        if ("base64".equalsIgnoreCase(type)) {
            try {
                byte[] decodedBytes = Base64.getDecoder().decode(data);
                return new String(decodedBytes);
            } catch (IllegalArgumentException error) {
                throw new IllegalArgumentException("Invalid Base64 encoding!");
            }
        }
        // For other types or fallback, return as-is:
        return data;
    }
}
