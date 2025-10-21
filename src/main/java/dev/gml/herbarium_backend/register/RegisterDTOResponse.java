package dev.gml.herbarium_backend.register;

import lombok.Builder;

@Builder
public record RegisterDTOResponse(
    String message,
    String email,
    Long userId
) {}
