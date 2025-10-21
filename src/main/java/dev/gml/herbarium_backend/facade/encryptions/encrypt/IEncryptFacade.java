package dev.gml.herbarium_backend.facade.encryptions.encrypt;

public interface IEncryptFacade {
    String encode(String type, String data);
}
