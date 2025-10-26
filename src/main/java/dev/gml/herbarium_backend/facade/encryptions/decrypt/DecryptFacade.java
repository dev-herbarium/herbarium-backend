package dev.gml.herbarium_backend.facade.encryptions.decrypt;

import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class DecryptFacade implements IDecryptFacade {
    
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
