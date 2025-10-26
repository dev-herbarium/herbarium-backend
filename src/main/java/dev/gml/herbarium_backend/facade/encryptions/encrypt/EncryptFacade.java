package dev.gml.herbarium_backend.facade.encryptions.encrypt;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Component
public class EncryptFacade implements IEncryptFacade {
    
    private final BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String type, String data) {
        if ("bcrypt".equalsIgnoreCase(type)) {
            return bcryptEncoder.encode(data);
        }
        // For other types of fallback, return "as-is":
        return data;
    }
}
