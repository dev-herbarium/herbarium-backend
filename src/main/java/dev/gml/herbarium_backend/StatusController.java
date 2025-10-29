package dev.gml.herbarium_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * <b>Status Controller for health checks and application monitoring</b>
 * 
 * <p>This controller demonstrates the basic structure of a Spring REST controller
 * and provides endpoints for basic health checks and application status verification.</p>
 * 
 * <p><b>Architecture Note:</b> The controller layer serves as the entry point for
 * all external HTTP requests, following the "Thin Controller, Thick Service" pattern
 * where business logic is delegated to service layers.</p>
 */
@RestController
@Tag(name = "❤️ Health Check", description = "Endpoints for verifying application status and health.")
public class StatusController {

    // private final: The standard practice for fields that hold dependencies.
    // It enforces immutability, ensuring the service reference cannot be changed.
    private final HerbariumJacocoService service;

    /**
     * <b> Constructor Injection </b>
     * <p>
     * This is the preferred way to handle dependencies in Spring. When Spring
     * creates an instance of this controller, it automatically finds the
     * required 'HerbariumJacocoService' bean and passes it into this constructor.
     * </p>
     * 
     * @param service The service for health status operations
     */
    public StatusController(HerbariumJacocoService service) {
        // Assigns the injected service instance to the local final field.
        this.service = service;
    }

    /**
     * <b> Health Check Endpoint </b>
     * <p>
     * Retrieves the current application status message by delegating the
     * request to the Service Layer (HerbariumJacocoService).
     * 
     * @return A simple status string ("Herbarium-Backend is Active!").
     */
    @GetMapping("/api/status")
    @Operation(
        summary = "Get application health status",
        description = "Returns a simple status string to verify the application is running correctly."
    )
    @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Application is running normally",
                content = @Content(
                    mediaType = "text/plain",
                    examples = {
                        @ExampleObject(value = "Herbarium-Backend is Active!")
            }))
    })
    public String getStatus() {
        // Delegate: The Controller's job is to route and handle HTTP concerns,
        // while the Service's job is to handle business logic. This line
        // follows the "Thin Controller, Thick Service" pattern.
        return service.getStatus();
    }
}
