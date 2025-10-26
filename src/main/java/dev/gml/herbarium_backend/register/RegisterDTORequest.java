package dev.gml.herbarium_backend.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDTORequest(
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid Email Format!")
    String email,

    @NotBlank(message = "Password is required!")
    @Size(min = 6, message = "The password is too short. It must be at least six characters!")
    String password,

    @NotBlank(message = "Password confirmation is required!")
    String confirmPassword
    
) {
    public boolean passwordsMatch() {
            return password != null && password.equals(confirmPassword);
    }
}
