package dev.gml.herbarium_backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * <b>OPENAPI (<a href="https://swagger.io/">Swagger</a>) Configuration for the
 * Herbarium Backend API. </b>
 * <p>
 * This class defines the metadata that will appear at the top of the
 * Swagger UI (API Documentation) page. It establishes the central
 * contract for the REST API consumed by the frontend application.
 */
@Configuration
// @Configuration: This is a Spring annotation that tells the Spring IoC
// Container that this class contains one or more methods annotated with
// @Bean, which should be processed to generate bean definitions
// and service requests at runtime. Without this, Spring ignores the file.
public class OpenApiConfig {

    /**
     * <p>
     * Defines the custom OpenAPI object with metadata. </b>
     * 
     * @return The configured OpenAPI object.
     */
    @Bean
    // @Bean: This method produces a bean (a reusable object) to be managed
    // by the Spring container. The Springdoc library automatically looks
    // for an OpenAPI bean to apply custom configuration metadata.
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // The main OpenAPI object that holds all documentation metadata.
                .info(new Info()
                        // Info: Contains the application-level metadata.

                        // title(): Sets the prominent title displayed at the top of the Swagger UI
                        // page.
                        .title("Herbarium Backend API")

                        // version(): Uses the project's version defined in pom.xml
                        .version("0.0.1-SNAPSHOT")

                        // description(): Provides the detailed explanatory text below the title.
                        .description("API documentation for the Herbarium Full-Stack Web Application backend. "
                                + "It manages plant specimens, associated data, and user security.")

                        // termsOfService(): Optional-provides a link to terms, good for professional
                        // appearance.
                        .termsOfService("http://swagger.io/terms/"));
    }

}

// Spring IoC (Inversion of Control)
// Spring's Inversion of Control (IoC) principle flips
// the script. With IoC, I delegate the responsibility
// of object creation and dependency management to the
// Spring container. Instead of creating objects explicitly,
// I define their dependencies through configuration files or
// annotations.