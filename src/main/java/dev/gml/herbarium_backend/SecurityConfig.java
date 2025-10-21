package dev.gml.herbarium_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * <b> Spring Security Configuration for development.</b>
 * <p>
 * This class customizes Spring Security's default behavior. Its primary
 * role is to explicitly whitelist paths needed for development and
 * documentation
 * (<a href="https://swagger.io/">Swagger</a> UI and
 * <a href="https://www.h2database.com/html/main.html">H2</a> Console) to
 * prevent
 * the application from redirecting unauthenticated request to a login form.
 */
@Configuration
// @Configuration: Marks this class as a source of bean definitions.
// Spring finds this and processes the @Bean methods inside.
@EnableWebSecurity
// @EnableWebSecurity: Enables Spring Security's web security support
// and provides the Spring MVC integration. It is essential for using the
// HttpSecurity object to configure rules.
public class SecurityConfig {

    @Value("${api-endpoint}")
    private String endpoint;

    /**
     * <b> Array of URL patterns that must be accessible without authentication.
     * </b>
     * <p>
     * Includes all paths required for API documentation and development tools.
     */
    private static final String[] SWAGGER_PATHS = {
            "/swagger-ui.html", // The main Swagger UI page.
            "/swagger-ui/**", // Resources like CSS, JS, and image files that feeds the UI.
            "/v3/api-docs/**", // The raw OpenAPI JSON/YAML file that feeds the UI.
            "/webjars/**", // Resources required by Swagger, often served via webjars.
            "/h2-console/**" // Allows access to the in-memory H2 database UI (for development only).
    };

    /**
     * <b> Defines the Security Filter Chain, which is the core of Spring Security.
     * </b>
     * <p>
     * This bean specifies the custom security rules that are applied to incoming
     * request.
     * 
     * @param http The HttpSecurity object used to build security configuration.
     * @return The configured {@link SecurityFilterChain} bean.
     * @throws Exception
     */
    @Bean
    // @Bean: Makes the resulting SecurityFilterChain object available in the Spring
    // context.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // ----------------------------------------------------------------------------
        // 1. CROSS-SITE REQUEST FORGERY (CSRF)
        // ----------------------------------------------------------------------------
        // CSRF must be disabled for the H2 console to function properly, as it
        // doesn't send a CSRF token. It is also often disabled for stateless
        // REST APIs using token-based security (like JWT).
        // NOTE: This is generally unsafe for browser-based (stateful) applications
        // and must be re-enabled or configured properly for production environments!
        // TODO: Revisit CSRF configuration for production!
        http.csrf(AbstractHttpConfigurer::disable);

        // ----------------------------------------------------------------------------
        // 2. AUTHORIZATION RULES
        // ----------------------------------------------------------------------------

        // Allow H2 console to be displayed in frames
        http.headers(headers -> headers
            .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );

        // Configure authorization rules (i.e., who can access which path).
        http.authorizeHttpRequests(authorize -> authorize
                // Allow unauthenticated access to all whitelisted paths (documentation/dev
                // tools)
                .requestMatchers(SWAGGER_PATHS).permitAll()
                .requestMatchers(HttpMethod.POST, endpoint + "/register").permitAll()

                // Temporarily permit all other requests (my actual API endpoints)
                // This makes development easier for now but means any API call is allowed.
                // TODO: This is generally unsafe. Must be changed to
                // `.anyRequest().authenticated()`
                // for production to enforce token-based security on all endpoints.
                .anyRequest().permitAll());

        // ---------------------------------------------------------------------------
        // 3. AUTHENTICATION MECHANISM
        // ---------------------------------------------------------------------------
        // Disable the default login form provided by Spring Security.
        // If this were left enabled, any request to a protected path would be
        // redirected to the browser-based sign-in form.
        http.formLogin(AbstractHttpConfigurer::disable);

        // Finalize the configuration and return the filter chain.
        return http.build();
    }

}
