package dev.gml.herbarium_backend.register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * <b> REGISTER CONTROLLER </b>
 * <p>
 * Handles user registration operations including validation and error handling.
 * This controller manages the complete user registration workflow.
 */
@RestController
@RequestMapping("${api-endpoint}")
@Tag(name = "✍ User Registration", description = "Endpoints for user registration and account creation.")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    /**
     * <b> Register New User </b>
     * <p>
     * Creates a new user account with the provided registration details.
     * Passwords must be Base64 encoded and will be hashed with BCrypt before
     * storage.
     * 
     * @param dto
     * @return
     */
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = """
            Creates a new user account with email and password.

            **Password Requirements:**
            - Must be at least 6 characters long (after Base64 decoding).
            - Must be Base64 encoded when sent to the API.
            - Will be automatically hashed with BCrypt for secure storage.

            **Validation Rules:**
            - Email must be valid and unique.
            - Password must match in both fields.
            - Password must meet minimum length requirement.
            """, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "User registration data", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterDTORequest.class), examples = {
            @ExampleObject(name = "Successful Registration", summary = "Valid registration request", value = """
                    {
                        "email": "user@example.com",
                        "password": "c2VjcmV0MTIz",
                        "confirmPassword": "c2VjcmV0MTIz"
                    }
                    """)
    })))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterDTOResponse.class), examples = {
                    @ExampleObject(name = "Success Response", value = """
                            {
                                "message": "User registered successfully",
                                "email": "user@example.com",
                                "userId": 1
                            }
                            """)
            })),
            @ApiResponse(responseCode = "400", description = "Registration failed due to validation errors", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterDTOResponse.class), examples = {
                    @ExampleObject(name = "Email already Exists", value = """
                            {
                                "message": "Registration failed: Email already registered",
                                "email": "user@example.com",
                                "userId": null
                            }
                            """),
                    @ExampleObject(name = "Invalid Email", value = """
                            {
                                "message": "Registration failed: Email should be valid",
                                "email": "invalid-email",
                                "userId": null
                            }
                            """),
                    @ExampleObject(name = "Password Mismatch", value = """
                            {
                                "message": "Registration failed: Passwords do not match",
                                "email": "user@example.com",
                                "userId": null
                            }
                            """)
            }))
    })
    public ResponseEntity<RegisterDTOResponse> registerUser(@Valid @RequestBody RegisterDTORequest dto) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RegisterDTOResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        var response = RegisterDTOResponse.builder()
                .message("Registration failed: " + errorMessage)
                .email(null)
                .userId(null)
                .build();

        return ResponseEntity.badRequest().body(response);
    }
}
