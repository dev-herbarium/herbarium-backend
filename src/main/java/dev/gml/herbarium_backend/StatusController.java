package dev.gml.herbarium_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * <b> STATUS CONTROLLER (MOCK) </b>
 * <p>
 * This file is for configuration &amp; testing purposes only! It demonstrates
 * the basic
 * structure of a Spring REST controller and allows for basic health checks.
 * <p>
 * The Controller layer is the entry point for all externall HTTP requests.
 */
@RestController
// @RestController: This is a composite annotation combining @Controller and
// @ResponseBody. It tells Spring:
// 1. This class handles web requests.
// 2. All method return values should be automatically serialized into
// the HTTP response body (e.g., JSON or, in this case, a simple string).
@Tag(name = "Health Check", description = "Endpoints for verifying application status and health.")
// @Tag (from Swagger): Defines a group/category for the API documentation
// in the Swagger UI. All operations in this controller will be listed under
// the "Health Check" category
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
    // @GetMapping: Maps HTTP GET request to the specified URL path.
    // When a user hits "http://localhost:8080/api/status", this method is executed.
    @Operation(summary = "Get application health status", description = "Returns a simple status string.")
    // @Operation (from Swagger): Provides detailed, per-method documentation
    // for the Swagger UI, including the short summary and the longer description.
    public String getStatus() {
        // Delegate: The Controller's job is to route and handle HTTP concerns,
        // while the Service's job is to handle business logic. This line
        // follows the "Thin Controller, Thick Service" pattern.
        return service.getStatus();
    }
}
