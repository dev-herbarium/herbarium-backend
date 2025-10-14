package dev.gml.herbarium_backend;

import org.springframework.stereotype.Service;

/**
 * <b>For configuration &amp; testing purposes only!</b>
 * <p>
 * Primarily for the <a href="https://www.eclemma.org/jacoco/">JaCoCo</a>
 * &amp; <a href="https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html">JavaDoc</a>
 * plugins.
 * <p>
 * Service layer component responsible for core Herbarium business logic,
 * primarily for status checks &amp; simple calculations.
 * 
 * @author gml
 * @version 1.0
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
