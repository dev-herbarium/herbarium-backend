package dev.gml.herbarium_backend;

import org.springframework.stereotype.Service;

/**
 * Service layer component for JaCoCo and JavaDoc plugin testing purposes.
 * 
 * <p><b>Primary use:</b> Configuration and testing for code coverage and documentation tools.
 * Contains core Herbarium business logic for status checks and simple calculations.</p>
 * 
 * @author gml
 * @version 1.0
 * @since 2025
 * @see <a href="https://www.eclemma.org/jacoco/">JaCoCo Code Coverage Tool</a>
 * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html">JavaDoc Tool</a>
 */
@Service
public class HerbariumJacocoService {

    /**
     * Retrieves the current active status message for the Herbarium backend.
     * This is a health check utility method.
     * 
     * @return A string indicating the service is active.
     */
    public String getStatus() {
        return "Herbarium-Backend is Active!";
    }

    /**
     * Performs a basic arithmetic calculation based on the sign of the first number.
     * Used for demonstration purposes.
     * 
     * @param a The first integer. If positive, it is added.
     * @param b The second integer to be added or subtracted.
     * @return The result of (a + b) if a > 0, otherwise (a - b).
     * @since 0.0.1-SNAPSHOT
     */
    public int calculate(int a, int b) {
        if (a > 0) {
            return a + b;
        } else {
            return a - b;
        }
    }
}
