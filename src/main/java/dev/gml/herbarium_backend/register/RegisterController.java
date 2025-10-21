package dev.gml.herbarium_backend.register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("${api-endpoint}")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTOResponse> registerUser(@RequestBody RegisterDTORequest dto) {
        try {
            var user = service.registerUser(dto);
            
            var response = RegisterDTOResponse.builder()
                .message("User registered successfully")
                .email(user.getEmail())
                .userId(user.getId())
                .build();
            
                return ResponseEntity.status(201).body(response);

        } catch (IllegalArgumentException e) {
            var response = RegisterDTOResponse.builder()

                .message("Registration failed: " + e.getMessage())
                .email(dto.email())
                .userId(null)
                .build();

            return ResponseEntity.badRequest().body(response);
        }
    }
}
