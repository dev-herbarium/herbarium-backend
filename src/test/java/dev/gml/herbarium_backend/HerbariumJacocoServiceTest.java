package dev.gml.herbarium_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;

// @SpringBootTest ensure the service is available.
@SpringBootTest
public class HerbariumJacocoServiceTest {

    @Autowired
    private HerbariumJacocoService service;

    @Test
    @DisplayName("JaCoCo Setup Test: 1. It should return 'Herbarium-Backend is Active!'")
    void getStatus_shouldReturnActiveMessage() {
        // This test covers the "getStatus" method:
        String expected = "Herbarium-Backend is Active!";

        assertEquals(expected, service.getStatus());
    }

    @Test
    @DisplayName("JaCoCo Setup Test: 2. It should only cover the first branch of the 'if/else' statement")
    void calculate_shouldCoverFirstBranch() {
        // This covers the 'if(a > 0)' branch:
        assertEquals(5, service.calculate(3, 2));
    }

    // Note: The 'else' branch of calculate is left uncoverd for now.
    // JaCoCo will flag this partial coverage.

}
