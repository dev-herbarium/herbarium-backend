package dev.gml.herbarium_backend.facade.encryptions.encrypt;

import org.springframework.stereotype.Component;

@Component
public class EncryptFacade implements IEncryptFacade {
    
    @Override
    public String encode(String type, String data) {
        // TODO: It should be implemented in the Future
        // Actually return the data "as-is",
        // Allowing the app to start without encryption dependencies.
        return data;
    }
}
