package dev.gml.herbarium_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>The main entry point for the Herbarium Backend Spring Boot Application.</b>
 * 
 * <p>This class is annotated with {@link org.springframework.boot.autoconfigure.SpringBootApplication}
 * which enables Spring Boot's auto-configuration, component scanning, and configuration properties
 * support. It serves as the starting point for the application's context and startup process.</p>
 * 
 * <p>When the application is run, it initializes the Spring ApplicationContext and starts
 * the embedded web server (if included in dependencies), making the application ready to
 * handle requests.</p>
 * 
 * <p>Typical usage involves running this class directly, which triggers the Spring Boot
 * startup process through the {@link #main(String[])} method.</p>
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 */
@SpringBootApplication
public class HerbariumBackendApplication {

	/**
     * Main method which serves as the entry point for the Spring Boot application.
     * 
     * <p>This method delegates to Spring Boot's {@link SpringApplication#run(Class, String...)}
     * method to bootstrap the application context.</p>
     * 
     * @param args Command line arguments passed to the application (optional)
     */
	public static void main(String[] args) {
		SpringApplication.run(HerbariumBackendApplication.class, args);
	}

}
