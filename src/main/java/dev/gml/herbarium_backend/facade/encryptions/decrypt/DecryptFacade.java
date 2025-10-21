package dev.gml.herbarium_backend.facade.encryptions.decrypt;

import org.springframework.stereotype.Component;

@Component
public class DecryptFacade implements IDecryptFacade {
    
    @Override
    public String decode(String type, String data) {
        // TODO: It should be implemented.
        // Actually it returns the data "as-is",
        // allowing the app to start without encryption dependencies.
        return data;
    }
}
